package ru.one.hhadvisor.program.models.modelforone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.one.hhadvisor.program.models.NullableExperience;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Experience {
        private String id;
        private String name;

        public Experience() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Experience(String id, String name) {
            this.id = id;
            this.name = name;
        }
        public static Object nullableObject(Object z) {
            if (z == null) return new NullableExperience();
            else return z;	}
}
