package attraction.repository;

import attraction.Gugun;

import java.util.List;

public interface GugunRepository {

    List<Gugun> findBySidoCode(int sidoCode);
}
