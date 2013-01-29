package org.springframework.data.simpledb.util.marshaller;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonMarshallerTest {

    private JsonMarshaller cut;

    @Before
    public void setUp() {
        cut = new JsonMarshaller();
    }

    @Test
    public void unmarshal_should_properly_unmarshal_a_json_string_into_the_target_class_instance() throws IOException {

        // Prepare
        String userJsonString = new String(IOUtils.toByteArray(this.getClass().getClassLoader().getResourceAsStream("org/springframework/data/simpledb/util/marshaller/user.json")));
        assertNotNull(userJsonString);

        // Exercise
        User expectedUser = cut.unmarshal(userJsonString, User.class);

        // Verify
        assertNotNull(expectedUser);
        assertEquals(User.Gender.MALE, expectedUser.getGender());
    }

    private static class User {
        public enum Gender {MALE, FEMALE}

        public static class Name {
            private String _first, _last;

            public String getFirst() {
                return _first;
            }

            public String getLast() {
                return _last;
            }

            public void setFirst(String s) {
                _first = s;
            }

            public void setLast(String s) {
                _last = s;
            }
        }

        private Gender _gender;
        private Name _name;
        private boolean _isVerified;
        private byte[] _userImage;

        public Name getName() {
            return _name;
        }

        public boolean isVerified() {
            return _isVerified;
        }

        public Gender getGender() {
            return _gender;
        }

        public byte[] getUserImage() {
            return _userImage;
        }

        public void setName(Name n) {
            _name = n;
        }

        public void setVerified(boolean b) {
            _isVerified = b;
        }

        public void setGender(Gender g) {
            _gender = g;
        }

        public void setUserImage(byte[] b) {
            _userImage = b;
        }
    }

}
