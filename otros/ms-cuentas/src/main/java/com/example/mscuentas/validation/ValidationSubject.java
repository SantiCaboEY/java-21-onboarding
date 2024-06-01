package com.example.mscuentas.validation;

import com.example.mscuentas.enums.AccountProduct;
import com.example.mscuentas.validation.providers.ValidationUnit;

import java.util.Arrays;
import java.util.List;

/**
 * A bundle of retrieved data ready to run validations
 * The retrieved data and the methods to run each validation rule ara stored un instances of {@link ValidationUnit}
 * There's one instance for each {@link com.example.mscuentas.validation.providers.ValidationProvider} called.
 * Each validation is run by chaining the filters provided by each {@link ValidationUnit}
 */
public class ValidationSubject {

    private final List<ValidationUnit> validationUnits;

    protected ValidationSubject(List<ValidationUnit> validationUnits) {
        this.validationUnits = validationUnits;
    }

    /**
     * Determine allowed products by filtering out products that are incompatible with provider data.
     * Each filter is stored in a {@link ValidationUnit} that contains a particular provider's logic.
     * @return the filtered list containing the allowed products.
     */
    public List<AccountProduct> getAllowedProducts(){
        var products = Arrays.asList(AccountProduct.values());
        for(ValidationUnit unit : this.validationUnits){
            products = unit.filterProducts(products);
        }
        return products;
    }
}
