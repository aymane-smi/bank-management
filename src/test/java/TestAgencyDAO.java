import com.bank.DAO.AgencyDAOImpl;
import com.bank.Entity.Agency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class TestAgencyDAO {
    @Test
    public void testCreate(){
        Agency agency = new Agency("", "name", "address", "123456789");
        Optional<Agency> optionalAgency = new AgencyDAOImpl().create(agency);
        optionalAgency.ifPresent((obj)->{
            Assertions.assertEquals("AGENCY2", obj.getCode());
        });
    }

    @Test
    public void testDelete(){
        int result = new AgencyDAOImpl().delete("AGENCY1");
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testUpdate(){
        Agency agency = new AgencyDAOImpl().findByCode("AGENCY2").get();
        agency.setName("name*");
        Optional<Agency> optionalAgency = new AgencyDAOImpl().update(agency);
        optionalAgency.ifPresent((obj)->{
            Assertions.assertEquals("name*", obj.getName());
        });
    }
}
