package com.xqin.component.exception;

import java.util.Collection;
import java.util.Map;

/**
 * Assertion utility class that assists in validating arguments.
 *
 * <p>Useful for identifying programmer errors early and clearly at runtime.
 *
 * <p>For example, if the contract of a public method states it does not
 * allow {@code null} arguments, {@code Assert} can be used to validate that
 * contract.
 *
 * For example:
 *
 * <pre class="code">
 * Assert.notNull(clazz, "The class must not be null");
 * Assert.isTrue(i > 0, "The value must be greater than zero");</pre>
 *
 * This class is empowered by  {@link org.springframework.util.Assert}
 *
 */
public interface Assert {

    BaseException newException();

    BaseException newException(Integer code, String msg);

    /**
     * Assert a boolean expression, throwing {@code BizException}
     *
     * for example
     *
     * <pre class="code">Assert.isTrue(i != 0, errorCode.B_ORDER_illegalNumber, "The order number can not be zero");</pre>
     *
     * @param expression a boolean expression
     * @throws BizException if expression is {@code false}
     */
    default void isTrue(boolean expression) {
        if (!expression) {
            throw newException();
        }
    }

    /**
     * Assert a boolean expression, throwing {@code BizException}
     *
     * for example
     *
     * <pre class="code">Assert.isTrue(i != 0, errorCode.B_ORDER_illegalNumber, "The order number can not be zero");</pre>
     *
     * @param expression a boolean expression
     * @param code
     * @param msg the exception message to use if the assertion fails
     * @throws BizException if expression is {@code false}
     */
    default void isTrue(boolean expression, Integer code, String msg) {
        if (!expression) {
            throw new BizException(code, msg);
        }
    }

    /**
     * Assert a boolean expression, if expression is true, throwing {@code BizException}
     *
     * for example
     *
     * <pre class="code">Assert.isFalse(i == 0, errorCode.B_ORDER_illegalNumber, "The order number can not be zero");</pre>
     *
     * This is more intuitive than isTure.
     */
    default void isFalse(boolean expression, Integer code, String msg) {
        if (expression) {
            throw new BizException(code, msg);
        }
    }

    default void isFalse(boolean expression, String msg) {
        if (expression) {
            throw new BizException(msg);
        }
    }

    default void isFalse(boolean expression) {
        isFalse(expression, "[Assertion failed] Must be false");
    }

    default void notNull(Object object, Integer code, String msg) {
        if (object == null) {
            throw new BizException(code, msg);
        }
    }

    default void notNull(Object object, String msg) {
        if (object == null) {
            throw new BizException(msg);
        }
    }

    default void notNull(Object object) {
        notNull(object, "[Assertion failed] Must not null");
    }

    default void notEmpty(Collection<?> collection, Integer code, String msg) {
        if (collection == null || collection.isEmpty()) {
            throw new BizException(code, msg);
        }
    }

    default void notEmpty(Collection<?> collection, String msg) {
        if (collection == null || collection.isEmpty()) {
            throw new BizException(msg);
        }
    }

    default void notEmpty(Collection<?> collection) {
        notEmpty(collection, "[Assertion failed] Collection must not be empty: it must contain at least 1 element");
    }

    default void notEmpty(Map<?, ?> map, Integer code, String msg) {
        if (map == null || map.isEmpty()) {
            throw new BizException(code, msg);
        }
    }

    default void notEmpty(Map<?, ?> map, String msg) {
        if (map == null || map.isEmpty()) {
            throw new BizException(msg);
        }
    }

    default void notEmpty(Map<?, ?> map) {
        notEmpty(map, "[Assertion failed] Map must not be empty: it must contain at least one entry");
    }

}
