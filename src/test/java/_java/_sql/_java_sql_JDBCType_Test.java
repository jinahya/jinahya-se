package _java._sql;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.JDBCType;
import java.sql.Types;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class _java_sql_JDBCType_Test {

    @DisplayName("every JDBCType has a constant of the same name defined in Types")
    @Test
    void _HasTypesConstantOfSameName_() {
        for (final var value : JDBCType.values()) {
            final var thrown = catchThrowable(() -> Types.class.getDeclaredField(value.name()));
            assertThat(thrown)
                    .as("Types.%1$s", value.name())
                    .isNull();
        }
    }

    @DisplayName("every JDBCType's vendor type number equals the constant defined in Types")
    @Test
    void _HasTypesConstantOfSameValue_() throws NoSuchFieldException, IllegalAccessException {
        for (final var value : JDBCType.values()) {
            final var constant = Types.class.getDeclaredField(value.name());
            assertThat(constant.getInt(null))
                    .as("Types.%1$s", value.name())
                    .isEqualTo(value.getVendorTypeNumber());
        }
    }

    @DisplayName("the number of JDBCType values equals the number of constants defined in Types")
    @Test
    void _MatchesNumberOfTypesConstants_() {
        assertThat(JDBCType.values()).hasSameSizeAs(_java_sql_Types_Test.constants());
    }
}
