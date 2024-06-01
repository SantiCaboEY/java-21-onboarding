package com.example.mscuentas.validation.providers;

import com.example.mscuentas.enums.AccountProduct;

import java.util.List;


/**
 * Executes filters that chain together to execute a rule.
 * Each {@link ValidationProvider} provides its own logic to execute the filters.
 */

public interface ValidationUnit {
    List<AccountProduct> filterProducts(List<AccountProduct> products);
}
