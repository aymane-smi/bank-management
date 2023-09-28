import com.bank.DAO.MissionDAOImpl;
import com.bank.Entity.Mission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class TestMissionDAO {
    @Test
    public void createMission(){
        Mission mission = new Mission(0,"name", "description");
        Optional<Mission> optionalMission = new MissionDAOImpl().create(mission);
        optionalMission.ifPresent((obj)->{
            Assertions.assertEquals(1, obj.getCode());
        });
    }
}
