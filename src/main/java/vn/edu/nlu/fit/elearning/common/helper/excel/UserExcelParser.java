package vn.edu.nlu.fit.elearning.common.helper.excel;

import org.apache.poi.ss.usermodel.Row;
import vn.edu.nlu.fit.elearning.common.utils.excel.ExcelCellUtils;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;

public class UserExcelParser {

    public static User parseRowToUser(Row row) {

        User user = new User();

        user.setFirstName(ExcelCellUtils.getString(row, 0));
        user.setLastName(ExcelCellUtils.getString(row, 1));
        user.setUsername(ExcelCellUtils.getString(row, 2));
        user.setEmail(ExcelCellUtils.getString(row, 3));
        user.setPassword(ExcelCellUtils.getString(row, 4));
        user.setPhone(ExcelCellUtils.getString(row, 5));

        return user;
    }
}