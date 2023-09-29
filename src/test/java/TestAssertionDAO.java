import com.bank.DAO.EmployeeDAOImpl;
import com.bank.DAO.MissionDAOImpl;
import com.bank.DAO.AssertionDAOImpl;
import com.bank.Entity.Mission;
import com.bank.Entity.MissionEmployee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class TestAssertionDAO {
    @Test
    public void testCreate(){
        MissionEmployee tmp = new MissionEmployee();
        tmp.setEmployee(new EmployeeDAOImpl().findByRegistrationNbr(11).get());
        tmp.setMission(new MissionDAOImpl().create(new Mission(0, "name", "description")).get());
        tmp.setStartDate(java.time.LocalDate.of(2023, 9, 28));
        tmp.setEndDate(java.time.LocalDate.of(2023, 9, 28));
        Optional<MissionEmployee> optionalMissionEmployee = new AssertionDAOImpl().create(tmp);
        optionalMissionEmployee.ifPresent((obj)->{
            Assertions.assertEquals(1, obj.getId());
        });
    }

    @Test
    public void testDelete(){
        int result = new AssertionDAOImpl().delete(1);
        Assertions.assertEquals(1, result);
    }
    @Test
    public void testFindByEmployee(){
        Optional<List<MissionEmployee>> optionalList = new AssertionDAOImpl().findByEmployee(11);
        optionalList.get().forEach((obj)->{
            System.out.println(String.format("*****   ID[%d] NOM_MISSION[%s] DATE_DEBUT[%s] DATE_FIN[%s]   *****", obj.getId(), obj.getMission().getName(), obj.getStartDate(), obj.getEndDate()));
        });
        Assertions.assertEquals(1, optionalList.get().size());
    }
}
