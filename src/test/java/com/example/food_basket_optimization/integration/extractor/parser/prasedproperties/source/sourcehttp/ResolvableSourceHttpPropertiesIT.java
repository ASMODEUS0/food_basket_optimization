package com.example.food_basket_optimization.integration.extractor.parser.prasedproperties.source.sourcehttp;

import com.example.food_basket_optimization.extractedentity.lenta.LentaCityExt;
import com.example.food_basket_optimization.extraction.ExtractedEntity;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.ResolvableSourceHttpProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.SourceHttpContract;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.HttpMethod;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlBasic;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlContextual;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.requestarguments.KeyValueUrlIterable;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlContextualPathProperties;
import com.example.food_basket_optimization.extraction.properties.source.sourcehttp.url.UrlProperties;
import com.example.food_basket_optimization.extraction.properties.util.RefValue;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.TestConstructor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class ResolvableSourceHttpPropertiesIT {


    private final ConcurrentMap<Class<? extends ExtractedEntity>, List<? extends ExtractedEntity>> extractContext = new ConcurrentHashMap<>();
    private final RefValue LENTA_CITY_REF_VALUE = new RefValue(LentaCityExt.class, "id");
    private final LentaCityExt LONDON_LENTA_CITY = new LentaCityExt("1", "LONDON");
    private final LentaCityExt MOSCOW_LENTA_CITY = new LentaCityExt("2", "MOSCOW");
    private final KeyValueUrlIterable TEST_KEY_VALUE_ITERABLE = new KeyValueUrlIterable("test1-iterable", "iterable=", 1, 2);
    private KeyValueUrlContextual TEST_KEY_VALUE_CONTEXTUAL;
    private KeyValueUrlBasic TEST_KEY_VALUE_BASIC = new KeyValueUrlBasic("test1-basic", "basic");
    private UrlProperties lentSoresUrlProperties;

    private ResolvableSourceHttpProperties resolvableSourceHttpProperties;


    @BeforeAll
    void prepare() {
        this.extractContext.put(LentaCityExt.class, List.of(LONDON_LENTA_CITY, MOSCOW_LENTA_CITY));
        TEST_KEY_VALUE_CONTEXTUAL = new KeyValueUrlContextual("test1-contextual", "contextual=", extractContext, LENTA_CITY_REF_VALUE);

        lentSoresUrlProperties = new UrlContextualPathProperties("https",
                List.of("api", "v2", "cities", LENTA_CITY_REF_VALUE.toString(), "stores"),
                "lenta.com",
                extractContext
        );


        resolvableSourceHttpProperties = new ResolvableSourceHttpProperties(List.of(TEST_KEY_VALUE_CONTEXTUAL, TEST_KEY_VALUE_ITERABLE, TEST_KEY_VALUE_BASIC),
                List.of(TEST_KEY_VALUE_CONTEXTUAL, TEST_KEY_VALUE_ITERABLE, TEST_KEY_VALUE_BASIC),
                HttpMethod.GET,
                lentSoresUrlProperties,
                "");
    }


    @Test
    void testThatSourceResolvingCorrectly() {

        List<SourceHttpContract> resolvedSources = resolvableSourceHttpProperties.resolve();
        assertThat(resolvedSources.size()).isEqualTo(32);
    }

    @Test
    @DisplayName("test that contextual url properties multiplying fully don't return nulls or redundant values")
    void testThatContextualUrlsMultiplyingCorrectly() {

        List<SourceHttpContract> resolvedSources = resolvableSourceHttpProperties.resolve();

        List<String> urlsStr = resolvedSources.stream().map(source -> source.getUrl().toString()).toList();

        List<String> distinctUrlsStr = urlsStr.stream().distinct().toList();


        assertAll(
                () -> assertThat(urlsStr.size()).isEqualTo(32),
                () -> assertThat(distinctUrlsStr).containsExactly("https://lenta.com/api/v2/cities/1/stores", "https://lenta.com/api/v2/cities/2/stores"),
                () -> assertThat(urlsStr).doesNotContainNull()
        );

    }


    @ParameterizedTest()
    @MethodSource("getArgumentsForKeyValueTests")
    @DisplayName("test that params multiplying fully, don't return nulls or redudant values")
    void testThatParamsMultiplyingCorrectly(List<KeyValueUrlBasic> params) {

        List<SourceHttpContract> resolvedSources = resolvableSourceHttpProperties.resolve();

        List<KeyValueUrlBasic> actualParams = resolvedSources.stream().map(SourceHttpContract::getHeaders).flatMap(Collection::stream).toList();

        assertThat(actualParams).containsAll(params);

    }

    @ParameterizedTest()
    @MethodSource("getArgumentsForKeyValueTests")
    @DisplayName("test that headers multiplying fully, don't return nulls or redudant values")
    void testThatHeadersMultiplyingCorrectly(List<KeyValueUrlBasic> headers) {

        List<SourceHttpContract> resolvedSources = resolvableSourceHttpProperties.resolve();

        List<KeyValueUrlBasic> actualParams = resolvedSources.stream().map(SourceHttpContract::getParams).flatMap(Collection::stream).toList();

        assertThat(actualParams).containsAll(headers);

    }


    static Stream<Arguments> getArgumentsForKeyValueTests() {
        return Stream.of(
                Arguments.of(List.of(
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=2"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=1"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2"),
                                new KeyValueUrlBasic("test1-basic", "basic"),
                                new KeyValueUrlBasic("test1-contextual", "contextual=1"),
                                new KeyValueUrlBasic("test1-iterable", "iterable=2")
                        )
                )
        );
    }


}