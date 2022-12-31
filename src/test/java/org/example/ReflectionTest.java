package org.example;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
    
    @DisplayName("어노테이션이 붙은 모든 클랙스 출력")
    @Test
    void componentScan() throws Exception {
        Set<Class<?>> beans = getTypesAnnotatedWIth(List.of(Controller.class, Service.class));

        logger.debug("beans: [{}]", beans);
    }

    private static Set<Class<?>> getTypesAnnotatedWIth(List<Class<? extends Annotation>> annotations) {
        final Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();

        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));
        return beans;
    }
    
    @DisplayName("클래스 정보 가져오기")
    @Test
    void showClass() throws Exception {
        Class<User> clazz = User.class;

        logger.debug("User all declared fields", Arrays.stream(clazz.getDeclaredFields()).collect(toList()));
        logger.debug("User all declared constructors", Arrays.stream(clazz.getConstructors()).collect(toList()));
        logger.debug("User all declared methods", Arrays.stream(clazz.getMethods()).collect(toList()));
    }

    @DisplayName("힙 영역에 로드된 클래스 타입의 객체를 가져오는 3가지 방법")
    @Test
    void load() throws Exception {

        Class<User> clazz1 = User.class;

        Class<? extends User> clazz2 = new User("dhlee", "이동현").getClass();

        Class<?> clazz3 = Class.forName("org.example.model.User");

        logger.debug("clazz1: {}", clazz1);
        logger.debug("clazz2: {}", clazz2);
        logger.debug("clazz3: {}", clazz3);

        assertEquals(clazz1, clazz2);
        assertEquals(clazz1, clazz3);
    }
}
