package com.example.food_basket_optimization.extraction.properties.source.sourcehttp;

import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.util.Multiplicator;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlBasicProperty;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlProperties;
import com.example.food_basket_optimization.extraction.properties.source.ResolvableSource;
import com.example.food_basket_optimization.extraction.properties.util.ExtractUtil;

import java.util.ArrayList;
import java.util.List;

public class ResolvableSourceHttpProperties extends SourceHttpPropertiesAbs implements ResolvableSource<SourceHttpContract> {

    private final List<Class<? extends ExtractedEntity>> refClasses = new ArrayList<>();


    public ResolvableSourceHttpProperties(List<? extends KeyValueUrlBasicProperty> params,
                                          List<? extends KeyValueUrlBasicProperty> headers,
                                          HttpMethod method,
                                          UrlProperties urlProperty,
                                          String body) {
        super(urlProperty, method, body, params, headers);
        //detects ref values
        refClasses.addAll(ExtractUtil.detectReferencesInListedObjects(params));
        refClasses.addAll(ExtractUtil.detectReferencesInListedObjects(headers));
        refClasses.addAll(ExtractUtil.detectObjectReferences(urlProperty));

    }



    @Override
    public List<SourceHttpContract> resolve() {
        List<UncheckedSourceHttpProperties> uncheckedSources = new ArrayList<>(List.of(new UncheckedSourceHttpProperties(this)));

        try {
            uncheckedSources = Multiplicator.resolveListedMultiplication(getParamProperties(), SourceHttpPropertiesAbs.class.getDeclaredField(PARAMS_FIELD_NAME), uncheckedSources);
            uncheckedSources = Multiplicator.resolveListedMultiplication(getHeaderProperties(), SourceHttpPropertiesAbs.class.getDeclaredField(HEADERS_FIELD_NAME), uncheckedSources);
            uncheckedSources = Multiplicator.resolveMultiplication(getUrlProperties(), SourceHttpPropertiesAbs.class.getDeclaredField(URL_FIELD_NAME), uncheckedSources);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return uncheckedSources.stream().map(UncheckedSourceHttpProperties::checkedConvert).toList();
    }


    @Override
    public List<Class<? extends ExtractedEntity>> getRefClasses() {
        return refClasses;
    }
}
