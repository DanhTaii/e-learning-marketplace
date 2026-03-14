package vn.edu.nlu.fit.elearning.feature.payment_method.dao;

import vn.edu.nlu.fit.elearning.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;

import java.util.List;

public class PaymentMethodDaoImpl extends BaseDao implements PaymentMethodDao {

    @Override
    public int create(PaymentMethod entity) {
        String sql = "INSERT INTO payment_methods (name, code, icon_url, status) " +
                "VALUES (:name, :code, :iconUrl, :status)";

        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("name", entity.getName())
                        .bind("code", entity.getCode())
                        .bind("iconUrl", entity.getIconUrl())
                        .bind("status", entity.getStatus())
                        .execute()
        );
    }

    @Override
    public PaymentMethod findById(Integer id) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(
                            "SELECT id, name, code, icon_url, created_at, updated_at " +
                                    "FROM payment_methods WHERE id = :id")
                    .bind("id", id)
                    .mapToBean(PaymentMethod.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    @Override
    public List<PaymentMethod> findByName(String name) {
        String nameSearch = "%" + name + "%";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(
                            "SELECT id, name, code, icon_url, status, created_at, updated_at " +
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
                            "SELECT id, name, code, icon_url, created_at, updated_at " +
                                    "FROM payment_methods " +
                                    "ORDER BY id")
                    .mapToBean(PaymentMethod.class)
                    .list();
        });
    }

    @Override
    public int update(PaymentMethod entity) {
        String sql = "UPDATE payment_methods \n" +
                "SET name = :name, \n" +
                "    code = :code, \n" +
                "    icon_url = :iconUrl, \n" +
                "    status = :status \n" +
                "WHERE id = :id";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bind("name", entity.getName())
                    .bind("code", entity.getCode())
                    .bind("iconUrl", entity.getIconUrl())
                    .bind("status", entity.getStatus())
                    .bind("id", entity.getId())
                    .execute();
        });
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM payment_methods WHERE id = :id";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bind("id", id)
                    .execute();
        });
    }
}
