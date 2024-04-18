package com.spring.tacospring.data;

import com.spring.tacospring.model.IngredientRef;
import com.spring.tacospring.model.Taco;
import lombok.RequiredArgsConstructor;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcTacoRepository implements TacoRepository {
    private final JdbcOperations jdbcOperations;

    @Override
    public long save(Long orderId, int orderKey, Taco taco) {
        PreparedStatementCreatorFactory pscf = getTacoCreatorFactory();
        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                taco.getCreatedAt(),
                                orderId,
                                orderKey));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);
        saveIngredientRefs(tacoId, taco.getIngredients());
        return tacoId;
    }

    private static PreparedStatementCreatorFactory getTacoCreatorFactory() {
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Taco "
                                + "(name, created_at, taco_order, taco_order_key) "
                                + "values (?, ?, ?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
                );
        pscf.setReturnGeneratedKeys(true);
        return pscf;
    }

    private void saveIngredientRefs(long tacoId, List<IngredientRef> ingredientRefs) {
        for (IngredientRef ingredientRef : ingredientRefs) {
            jdbcOperations.update(
                    "insert into Ingredient_Ref (taco_id, ingredient_id) values (?, ?)",
                    tacoId,
                    ingredientRef.getIngredient().getId());
        }
    }
}
