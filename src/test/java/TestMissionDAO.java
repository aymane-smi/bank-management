import com.bank.DAO.MissionDAOImpl;
import com.bank.Entity.Mission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class TestMissionDAO {
    @Test
    public void testCreateMission(){
        Mission mission = new Mission(0,"name", "description");
        Optional<Mission> optionalMission = new MissionDAOImpl().create(mission);
        optionalMission.ifPresent((obj)->{
            Assertions.assertEquals(1, obj.getCode());
        });
    }
    @Test
    public void testDeleteMission(){
        int result = new MissionDAOImpl().delete(1);
        Assertions.assertEquals(1, result);
    }
    @Test
    public void testFindAll(){
        Optional<List<Mission>> optionalMissionList = new MissionDAOImpl().findAll();
        optionalMissionList.ifPresent((list)->{
            Assertions.assertEquals(list.size(), 0);
        });
    }
}
