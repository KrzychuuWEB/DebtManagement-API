package pl.krzychuuweb.debtmanagment.debtor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.krzychuuweb.debtmanagment.debtor.dto.DebtorDTO;
import pl.krzychuuweb.debtmanagment.exception.NotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DebtorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DebtorController debtorController;
    @Autowired
    private DebtorQueryRepository debtorQueryRepository;

    @Test
    @Transactional
    void should_get_all_debtors() throws Exception {
        List<Debtor> debtorList = new ArrayList<>();
        debtorList.add(TestDebtorBuilder.newDebtor().build());
        debtorList.add(TestDebtorBuilder.newDebtor().but().withFirstName("DebtorFirstName").build());

        debtorQueryRepository.saveAll(debtorList);

        MvcResult mvcResult = mockMvc.perform(get("/debtors"))
                .andExpect(status().is(200))
                .andReturn();

        List<DebtorDTO> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertThat(response).hasSize(2);
        assertThat(response.get(0).firstName()).isEqualTo(TestDebtorBuilder.DEFAULT_FIRSTNAME);
    }

    @Test
    @Transactional
    void should_get_debtor_by_id() throws Exception {
        Debtor debtor = debtorQueryRepository.save(TestDebtorBuilder.newDebtor().build());

        MvcResult mvcResult = mockMvc.perform(get("/debtors/" + debtor.getId()))
                .andExpect(status().is(200))
                .andReturn();

        DebtorDTO response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), DebtorDTO.class);

        assertThat(response.id()).isEqualTo(debtor.getId());
        assertThat(response.firstName()).isEqualTo(debtor.getFirstName());
        assertThat(response.lastName()).isEqualTo(debtor.getLastName());
    }

    @Test
    @Transactional
    void should_delete_debtor_by_id() throws Exception {
        Debtor debtor = debtorQueryRepository.save(TestDebtorBuilder.newDebtor().build());

        MvcResult mvcResult = mockMvc.perform(delete("/debtors/" + debtor.getId()))
                .andExpect(status().is(200))
                .andReturn();

        mockMvc.perform(get("/debtors/" + debtor.getId()))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andReturn();
    }
}