package _java._sql;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.JDBCType;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class _java_sql_Types_Test {

    /**
     * Returns all constants defined in the {@link Types} class.
     *
     * @return a list of all constants defined in the {@link Types} class.
     */
    static List<Field> constants() {
        return Arrays.stream(Types.class.getDeclaredFields())
                .filter(f -> {
                    final var modifiers = f.getModifiers();
                    return Modifier.isPublic(modifiers)
                           && Modifier.isStatic(modifiers)
                           && Modifier.isFinal(modifiers);
                })
                .filter(f -> f.getType() == int.class)
                .toList();
    }

    @DisplayName("every constant defined in Types has a JDBCType of the same name")
    @Test
    void _HasJdbcTypeOfSameName_() {
        for (final var constant : constants()) {
            assertThat(JDBCType.valueOf(constant.getName()))
                    .as("JDBCType for Types.%1$s", constant.getName())
                    .isNotNull();
        }
    }

    @DisplayName("every constant defined in Types has a JDBCType of the same vendor type number")
    @Test
    void _HasJdbcTypeOfSameVendorTypeNumber_() throws IllegalAccessException {
        for (final var constant : constants()) {
            final var value = constant.getInt(null);
            assertThat(JDBCType.valueOf(constant.getName()).getVendorTypeNumber())
                    .as("vendorTypeNumber of JDBCType.%1$s", constant.getName())
                    .isEqualTo(value);
        }
    }

    @DisplayName("the number of constants defined in Types equals the number of JDBCType values")
    @Test
    void _MatchesNumberOfJdbcTypeValues_() {
        assertThat(constants()).hasSameSizeAs(JDBCType.values());
    }
}
