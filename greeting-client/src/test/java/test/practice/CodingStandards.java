package test.practice;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CodingStandards {

//    1. Avoid using ifs and for
//    2. Know when to use Optional.of and Optional.ofNullable
//    3. Mixing Jav8 stream and reacting stream
//        when to mix and when not some rules
//          when we have io calls db/api result in reactive stream we have to reactive stream
//    4. Avoid null check
//    5. Use Reactive chain as much as possible
//    6. use reactive chain as much as possible
//    7. use of Private method in service classes
//            business helper logic should be included in Domain/model class.
//            if can not write in service class
//    8. Include jira id in commit message

//    9. use Assertj library like AssertThat
//    10. use BDDMockito instead Mockito mock
//          as given will return (BDDMockito)  better than when then
//    11. use Junit5 for writing test cases like timeout, easily extend third party,
//    12. Extract hard coded string to string constant -> if one time use also and method using 100 times , tit will created 1000
//    13. before pushing 100% coverage use tracing for test coverage
//    14. TestHelper class should be used for common test code
//    15. Avoid multi line lambda expressions




    @Test
    void shouldThrowException() {
        String value = null;

        ThrowingCallable throwingCallable = () -> Optional.of(value);
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(throwingCallable);

    }

    @Test
    void shouldNotThrowException() {
        String value = null;
        Optional<String> value1 = Optional.ofNullable(value);

        ThrowingCallable throwingCallable = () -> Optional.ofNullable(value);
        assertDoesNotThrow(() -> throwingCallable);
        assertThat(value1.isPresent()).isFalse();
        assertThat(value1.isEmpty()).isTrue();

    }
}
