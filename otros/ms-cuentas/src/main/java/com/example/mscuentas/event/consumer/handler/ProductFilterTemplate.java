package com.example.mscuentas.event.consumer.handler;

import com.example.mscuentas.enums.AccountProduct;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;


@Getter
public class ProductFilterTemplate {
    private List<AccountProduct> availableProducts;
    private final List<Rule<?>> rules;

    public static ProductFilterTemplate create(){
        return new ProductFilterTemplate();
    }

    private ProductFilterTemplate(){
        rules = new ArrayList<>();
        availableProducts = Arrays.asList(AccountProduct.values());
    }

    public <T> ProductFilterTemplate loadRule(final CompletableFuture<T> parameterFuture,
                                              final Predicate<T> conditionToDisableProducts,
                                              final AccountProduct... productsToDisable){
        rules.add(new Rule<T>(Arrays.asList(productsToDisable), parameterFuture, conditionToDisableProducts));
        return this;
    }

    public List<AccountProduct> getAvailableProducts() {
        CompletableFuture<?>[] futures = rules.stream().map(r -> r.parameterFuture).toArray(CompletableFuture<?>[]::new);
        CompletableFuture.allOf(futures);
        rules.forEach(r -> {
            r.extractValue();
            if(r.test()){
                availableProducts = availableProducts.stream().filter(p -> !r.disabledProducts.contains(p)).toList();
            }
        });
        return availableProducts;
    }

    private static class Rule<T>{
            private final List<AccountProduct> disabledProducts;
            private final CompletableFuture<T> parameterFuture;
            private final Predicate<T> predicate;

            private T value;

        public Rule(List<AccountProduct> disabledProducts, CompletableFuture<T> parameterFuture,
                    Predicate<T> predicate) {
            this.disabledProducts = disabledProducts;
            this.parameterFuture = parameterFuture;
            this.predicate = predicate;
        }

        private void extractValue(){
            try {
                value = Objects.requireNonNull(parameterFuture.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        private boolean test(){
            return predicate.test(Objects.requireNonNull(value));
        }
    };

}
