package vn.edu.nlu.fit.elearning.feature.payment_method.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.payment.PaymentMethodFilter;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentMethodDaoImpl extends BaseDao implements PaymentMethodDao {

    @Override
    public PaymentMethod findById(Integer id) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(
                            "SELECT id, name, code, icon_url,status, created_at, updated_at " +
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
                                    "FROM payment_methods " +
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
                            "SELECT id, name, code, icon_url,status,created_at, updated_at " +
                                    "FROM payment_methods " +
                                    "ORDER BY status = 'ACTIVE' DESC")
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
    @Override
    public List<PaymentMethod> findPaymentMethodsByFilter(PaymentMethodFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildPaymentMethodWhereClause(filter, params);

        String sql = "SELECT id, name, code, icon_url, status, created_at, updated_at FROM payment_methods "
                + whereClause
                + " ORDER BY created_at DESC LIMIT :limit OFFSET :offset";

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql);
            params.forEach(query::bind);
            query.bind("limit", filter.getSize());
            query.bind("offset", (filter.getPage() - 1) * filter.getSize());
            return query.mapToBean(PaymentMethod.class).list();
        });
    }

    @Override
    public int countPaymentMethodsByFilter(PaymentMethodFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String where = buildPaymentMethodWhereClause(filter, params);

        String sql = "SELECT COUNT(*) FROM payment_methods " + where;

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql);
            params.forEach(query::bind);
            return query.mapTo(Integer.class).one();
        });
    }

    private String buildPaymentMethodWhereClause(PaymentMethodFilter filter, Map<String, Object> params) {
        StringBuilder where = new StringBuilder(" WHERE 1=1");

        if (filter.getName() != null && !filter.getName().trim().isEmpty()) {
            where.append(" AND name LIKE :nameSearch");
            params.put("nameSearch", "%" + filter.getName().trim() + "%");
        }

        if (filter.getStatus() != null) {
            where.append(" AND status = :status");
            params.put("status", filter.getStatus().name());
        }

        return where.toString();
    }

    @Override
    public int countAllPaymentMethods() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT COUNT(*) FROM payment_methods")
                    .mapTo(Integer.class)
                    .one();
        });
    }
}
