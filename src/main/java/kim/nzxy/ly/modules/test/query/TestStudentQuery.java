package kim.nzxy.ly.modules.test.query;

import kim.nzxy.ly.common.annotation.MbpQuery;
import kim.nzxy.ly.common.enums.QueryTypeEnum;

import java.time.LocalDate;

/**
 * 学生检索
 *
 * @param stuNum      学号
 * @param sex         性别
 * @param birthdayMin 最小生日
 * @param birthdayMax 最大生日
 * @param starCount   受表扬次数
 */
public record TestStudentQuery(
        @MbpQuery(type = QueryTypeEnum.LIKE)
        String stuNum,
        @MbpQuery(type = QueryTypeEnum.EQ)
        String sex,
        @MbpQuery(type = QueryTypeEnum.BETWEEN)
        LocalDate birthdayMin,
        @MbpQuery(type = QueryTypeEnum.BETWEEN)
        LocalDate birthdayMax,
        @MbpQuery(type = QueryTypeEnum.EQ)
        Integer starCount
) {
}