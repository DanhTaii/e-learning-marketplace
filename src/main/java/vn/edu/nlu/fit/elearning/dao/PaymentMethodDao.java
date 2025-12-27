package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.PaymentMethod;

import java.util.List;

public class PaymentMethodDao extends BaseDao implements BaseCrudDao<PaymentMethod, Integer> {

    @Override
    public int create(PaymentMethod entity) {
        String sql = "INSERT INTO Payment_Methods (name, code, icon_url, is_active) \n" +
                "VALUES (:name, :code, :iconUrl, :active)";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bindBean(entity)
                    .execute();
        });
    }

    @Override
    public PaymentMethod findById(Integer id) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(
                            "SELECT id, name, code, icon_url, is_active " +
                                    "FROM Payment_Methods WHERE id = :id")
                    .bind("id", id)
                    .mapToBean(PaymentMethod.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    // giống findByName của TagDao
    public List<PaymentMethod> findByName(String name) {
        String nameSearch = "%" + name + "%";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(
                            "SELECT id, name, code, icon_url, is_active " +
                                    "FROM Payment_Methods " +
                                    "WHERE name LIKE :nameSearch")
                    .bind("nameSearch", nameSearch)
                    .mapToBean(PaymentMethod.class)
                    .list();
        });
    }

    @Override
    public List<PaymentMethod> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(
                            "SELECT id, name, code, icon_url, is_active " +
                                    "FROM Payment_Methods " +
                                    "ORDER BY id")
                    .mapToBean(PaymentMethod.class)
                    .list();
        });
    }

    @Override
    public int update(PaymentMethod entity) {
        String sql = "UPDATE Payment_Methods \n" +
                "SET name = :name, \n" +
                "    code = :code, \n" +
                "    icon_url = :iconUrl, \n" +
                "    is_active = :active \n" +
                "WHERE id = :id";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bind("name", entity.getName())
                    .bind("code", entity.getCode())
                    .bind("iconUrl", entity.getIconUrl())
                    .bind("active", entity.isActive())
                    .bind("id", entity.getId())
                    .execute();
        });
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Payment_Methods WHERE id = :id";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bind("id", id)
                    .execute();
        });
    }
}
