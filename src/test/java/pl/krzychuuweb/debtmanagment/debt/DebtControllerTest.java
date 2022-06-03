package pl.krzychuuweb.debtmanagment.debt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.krzychuuweb.debtmanagment.debt.dto.DebtCreateDTO;
import pl.krzychuuweb.debtmanagment.debt.dto.DebtDTO;
import pl.krzychuuweb.debtmanagment.debt.dto.DebtDevotedDTO;
import pl.krzychuuweb.debtmanagment.debt.dto.DebtEditDTO;
import pl.krzychuuweb.debtmanagment.debtor.Debtor;
import pl.krzychuuweb.debtmanagment.debtor.DebtorFacade;
import pl.krzychuuweb.debtmanagment.debtor.TestDebtorBuilder;
import pl.krzychuuweb.debtmanagment.exception.NotFoundException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DebtControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DebtRepository debtRepository;
    @Autowired
    private DebtorFacade debtorFacade;

    @Test
    @Transactional
    void should_get_all_debt() throws Exception {
        Debtor debtor = debtorFacade.addDebtor(TestDebtorBuilder.newDebtor().build());
        Debtor debtor2 = debtorFacade.addDebtor(TestDebtorBuilder.newDebtor().but().withId(debtor.getId() + 1L).build());
        List<Debt> debtList = List.of(
                TestDebtBuilder.newDebt().but().withDebtor(debtor).build(),
                TestDebtBuilder.newDebt().but().withId(null).withName("ExampleDebtName2").but().withDebtor(debtor2).build()
        );

        debtRepository.saveAll(debtList);

        MvcResult result = mockMvc.perform(get("/debts"))
                .andExpect(status().is(200))
                .andReturn();

        List<DebtDTO> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertThat(response).hasSize(debtList.size());
    }

    @Test
    @Transactional
    void should_get_debt_by_id() throws Exception {
        Debtor debtor = debtorFacade.addDebtor(TestDebtorBuilder.newDebtor().build());
        Debt savedDebt = debtRepository.save(TestDebtBuilder.newDebt().but().withDebtor(debtor).build());

        MvcResult result = mockMvc.perform(get("/debts/" + savedDebt.getId()))
                .andExpect(status().is(200))
                .andReturn();

        DebtDTO response = objectMapper.readValue(result.getResponse().getContentAsString(), DebtDTO.class);

        assertThat(response.id()).isEqualTo(savedDebt.getId());
        assertThat(response.isDevoted()).isEqualTo(savedDebt.isDevoted());
    }

    @Test
    @Transactional
    void should_create_debt() throws Exception {
        Debtor debtor = debtorFacade.addDebtor(TestDebtorBuilder.newDebtor().build());

        DebtCreateDTO debtCreateDTO = new DebtCreateDTO("Example name", "example description", BigDecimal.valueOf(150), debtor.getId());

        MvcResult result = mockMvc.perform(post("/debts/")
                        .content(
                                objectMapper.writeValueAsString(debtCreateDTO)
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(201))
                .andReturn();

        DebtDTO response = objectMapper.readValue(result.getResponse().getContentAsString(), DebtDTO.class);

        assertThat(response.isDevoted()).isFalse();
        assertThat(response.name()).isEqualTo(debtCreateDTO.name());
        assertThat(response.debtor().id()).isEqualTo(debtor.getId());
    }

    @Test
    @Transactional
    void should_edit_debt() throws Exception {
        Debtor debtor = debtorFacade.addDebtor(TestDebtorBuilder.newDebtor().build());
        Debt debt = debtRepository.save(TestDebtBuilder.newDebt().but().withDebtor(debtor).build());
        DebtEditDTO debtEditDTO = new DebtEditDTO(debt.getId(), "EditedName", "EditedDescription", BigDecimal.valueOf(100));

        MvcResult result = mockMvc.perform(put("/debts/" + debt.getId())
                        .content(
                                objectMapper.writeValueAsString(debtEditDTO)
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(200))
                .andReturn();

        DebtDTO response = objectMapper.readValue(result.getResponse().getContentAsString(), DebtDTO.class);

        assertThat(response.name()).isEqualTo(debtEditDTO.name());
        assertThat(response.description()).isEqualTo(debtEditDTO.description());
        assertThat(response.price()).isEqualTo(debtEditDTO.price());
    }

    @Test
    @Transactional
    void should_delete_debt() throws Exception {
        Debt debt = debtRepository.save(TestDebtBuilder.newDebt().build());

        mockMvc.perform(delete("/debts/" + debt.getId()))
                .andExpect(status().is(200))
                .andReturn();

        mockMvc.perform(get("/debts/" + debt.getId()))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andReturn();
    }

    @Test
    @Transactional
    void should_change_devoted() throws Exception {
        Debt debt = debtRepository.save(TestDebtBuilder.newDebt().but().withIsDevoted(false).build());
        DebtDevotedDTO debtDevotedDTO = new DebtDevotedDTO(debt.getId(), true);

        MvcResult result = mockMvc.perform(put("/debts/" + debt.getId() + "/devoted")
                        .content(
                                objectMapper.writeValueAsString(debtDevotedDTO)
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(200))
                .andReturn();

        DebtDevotedDTO response = objectMapper.readValue(result.getResponse().getContentAsString(), DebtDevotedDTO.class);

        assertThat(response.devoted()).isTrue();
    }
}