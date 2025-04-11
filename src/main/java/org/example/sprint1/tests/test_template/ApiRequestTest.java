package org.example.sprint1.tests.test_template;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
Дополните шаблонный тест для проверки API-запросов, который проверяет, что переданный URL соответствует требованиям безопасности,
а метод запроса является допустимым.
 */
public class ApiRequestTest {

    @TestTemplate
    @ExtendWith(ApiInvocationContextProvider.class)
    void testApiRequest(String apiUrl, String httpMethod) {
        // Проверка, что URL начинается с "https://"                        
        assertTrue(apiUrl.startsWith("https://"), "URL должен начинаться с 'https://'");
        // Проверка, что метод поддерживается (GET или POST)                        
        assertTrue(httpMethod.equals("GET") || httpMethod.equals("POST"), "Метод должен быть GET или POST");
    }
}

// Поставщик контекста для различных конфигураций API
class ApiInvocationContextProvider implements TestTemplateInvocationContextProvider {

    /**
     *     этот метод проверяет, предназначен ли текущий тест для данного экстеншена.
     *     Если экстеншен используется над классом, этот метод позволяет отфильтровать те тесты, которые не поддерживают работу с шаблоном,
     *     возвращая false для неподходящих случаев. Если же экстеншен стоит над конкретным методом, метод обычно возвращает true,
     *     активируя поставщика для работы с тестом.
     */
    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true;
    }

    /**
     * возвращает поток объектов TestTemplateInvocationContext, где каждый объект представляет уникальный набор условий выполнения теста.
     */
    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        return Stream.of(
                new ApiTestTemplateInvocationContext("https://api.example.com/resource1", "GET"),
                new ApiTestTemplateInvocationContext("https://api.example.com/resource2", "POST"),
                new ApiTestTemplateInvocationContext("https://api.example.com/resource3", "GET")
        );
    }

    private static final class ApiTestTemplateInvocationContext implements TestTemplateInvocationContext, ParameterResolver {
        private final String apiUrl;
        private final String httpMethod;

        private ApiTestTemplateInvocationContext(String apiUrl, String httpMethod) {
            this.apiUrl = apiUrl;
            this.httpMethod = httpMethod;
        }

        @Override
        public String getDisplayName(int invocationIndex) {
            return "Тест API: " + apiUrl + " [" + httpMethod + "]";
        }

        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
            return (parameterContext.getParameter().getType().equals(String.class));
        }

        @Override
        public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
            return parameterContext.getIndex() == 0 ? apiUrl : httpMethod;
        }

        @Override
        public List<Extension> getAdditionalExtensions() {
            return List.of(this); // Возвращаем контекст как ParameterResolver        
        }
    }
}