package ru.otus.model;

public class Message {
    private final long id;
    private final String field1;
    private final String field2;
    private final String field3;
    private final String field4;
    private final String field5;
    private final String field6;
    private final String field7;
    private final String field8;
    private final String field9;
    private final String field10;
    private final String field11;
    private final String field12;
    private final ObjectForMessage field13;

    public Message(long id, String field1, String field2, String field3, String field4, String field5, String field6, String field7, String field8, String field9, String field10, String field11, String field12, ObjectForMessage field13) {
        this.id = id;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field5 = field5;
        this.field6 = field6;
        this.field7 = field7;
        this.field8 = field8;
        this.field9 = field9;
        this.field10 = field10;
        this.field11 = field11;
        this.field12 = field12;
        this.field13 = field13;
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    public long getId() {
        return this.id;
    }

    public String getField1() {
        return this.field1;
    }

    public String getField2() {
        return this.field2;
    }

    public String getField3() {
        return this.field3;
    }

    public String getField4() {
        return this.field4;
    }

    public String getField5() {
        return this.field5;
    }

    public String getField6() {
        return this.field6;
    }

    public String getField7() {
        return this.field7;
    }

    public String getField8() {
        return this.field8;
    }

    public String getField9() {
        return this.field9;
    }

    public String getField10() {
        return this.field10;
    }

    public String getField11() {
        return this.field11;
    }

    public String getField12() {
        return this.field12;
    }

    public ObjectForMessage getField13() {
        return this.field13;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Message)) return false;
        final Message other = (Message) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$field1 = this.getField1();
        final Object other$field1 = other.getField1();
        if (this$field1 == null ? other$field1 != null : !this$field1.equals(other$field1)) return false;
        final Object this$field2 = this.getField2();
        final Object other$field2 = other.getField2();
        if (this$field2 == null ? other$field2 != null : !this$field2.equals(other$field2)) return false;
        final Object this$field3 = this.getField3();
        final Object other$field3 = other.getField3();
        if (this$field3 == null ? other$field3 != null : !this$field3.equals(other$field3)) return false;
        final Object this$field4 = this.getField4();
        final Object other$field4 = other.getField4();
        if (this$field4 == null ? other$field4 != null : !this$field4.equals(other$field4)) return false;
        final Object this$field5 = this.getField5();
        final Object other$field5 = other.getField5();
        if (this$field5 == null ? other$field5 != null : !this$field5.equals(other$field5)) return false;
        final Object this$field6 = this.getField6();
        final Object other$field6 = other.getField6();
        if (this$field6 == null ? other$field6 != null : !this$field6.equals(other$field6)) return false;
        final Object this$field7 = this.getField7();
        final Object other$field7 = other.getField7();
        if (this$field7 == null ? other$field7 != null : !this$field7.equals(other$field7)) return false;
        final Object this$field8 = this.getField8();
        final Object other$field8 = other.getField8();
        if (this$field8 == null ? other$field8 != null : !this$field8.equals(other$field8)) return false;
        final Object this$field9 = this.getField9();
        final Object other$field9 = other.getField9();
        if (this$field9 == null ? other$field9 != null : !this$field9.equals(other$field9)) return false;
        final Object this$field10 = this.getField10();
        final Object other$field10 = other.getField10();
        if (this$field10 == null ? other$field10 != null : !this$field10.equals(other$field10)) return false;
        final Object this$field11 = this.getField11();
        final Object other$field11 = other.getField11();
        if (this$field11 == null ? other$field11 != null : !this$field11.equals(other$field11)) return false;
        final Object this$field12 = this.getField12();
        final Object other$field12 = other.getField12();
        if (this$field12 == null ? other$field12 != null : !this$field12.equals(other$field12)) return false;
        final Object this$field13 = this.getField13();
        final Object other$field13 = other.getField13();
        if (this$field13 == null ? other$field13 != null : !this$field13.equals(other$field13)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Message;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * PRIME + (int) ($id >>> 32 ^ $id);
        final Object $field1 = this.getField1();
        result = result * PRIME + ($field1 == null ? 43 : $field1.hashCode());
        final Object $field2 = this.getField2();
        result = result * PRIME + ($field2 == null ? 43 : $field2.hashCode());
        final Object $field3 = this.getField3();
        result = result * PRIME + ($field3 == null ? 43 : $field3.hashCode());
        final Object $field4 = this.getField4();
        result = result * PRIME + ($field4 == null ? 43 : $field4.hashCode());
        final Object $field5 = this.getField5();
        result = result * PRIME + ($field5 == null ? 43 : $field5.hashCode());
        final Object $field6 = this.getField6();
        result = result * PRIME + ($field6 == null ? 43 : $field6.hashCode());
        final Object $field7 = this.getField7();
        result = result * PRIME + ($field7 == null ? 43 : $field7.hashCode());
        final Object $field8 = this.getField8();
        result = result * PRIME + ($field8 == null ? 43 : $field8.hashCode());
        final Object $field9 = this.getField9();
        result = result * PRIME + ($field9 == null ? 43 : $field9.hashCode());
        final Object $field10 = this.getField10();
        result = result * PRIME + ($field10 == null ? 43 : $field10.hashCode());
        final Object $field11 = this.getField11();
        result = result * PRIME + ($field11 == null ? 43 : $field11.hashCode());
        final Object $field12 = this.getField12();
        result = result * PRIME + ($field12 == null ? 43 : $field12.hashCode());
        final Object $field13 = this.getField13();
        result = result * PRIME + ($field13 == null ? 43 : $field13.hashCode());
        return result;
    }

    public String toString() {
        return "Message(id=" + this.getId() + ", field1=" + this.getField1() + ", field2=" + this.getField2() + ", field3=" + this.getField3() + ", field4=" + this.getField4() + ", field5=" + this.getField5() + ", field6=" + this.getField6() + ", field7=" + this.getField7() + ", field8=" + this.getField8() + ", field9=" + this.getField9() + ", field10=" + this.getField10() + ", field11=" + this.getField11() + ", field12=" + this.getField12() + ", field13=" + this.getField13() + ")";
    }

    public MessageBuilder toBuilder() {
        return new MessageBuilder().id(this.id).field1(this.field1).field2(this.field2).field3(this.field3).field4(this.field4).field5(this.field5).field6(this.field6).field7(this.field7).field8(this.field8).field9(this.field9).field10(this.field10).field11(this.field11).field12(this.field12).field13(this.field13);
    }

    public static class MessageBuilder {
        private long id;
        private String field1;
        private String field2;
        private String field3;
        private String field4;
        private String field5;
        private String field6;
        private String field7;
        private String field8;
        private String field9;
        private String field10;
        private String field11;
        private String field12;
        private ObjectForMessage field13;

        MessageBuilder() {
        }

        public Message.MessageBuilder id(long id) {
            this.id = id;
            return this;
        }

        public Message.MessageBuilder field1(String field1) {
            this.field1 = field1;
            return this;
        }

        public Message.MessageBuilder field2(String field2) {
            this.field2 = field2;
            return this;
        }

        public Message.MessageBuilder field3(String field3) {
            this.field3 = field3;
            return this;
        }

        public Message.MessageBuilder field4(String field4) {
            this.field4 = field4;
            return this;
        }

        public Message.MessageBuilder field5(String field5) {
            this.field5 = field5;
            return this;
        }

        public Message.MessageBuilder field6(String field6) {
            this.field6 = field6;
            return this;
        }

        public Message.MessageBuilder field7(String field7) {
            this.field7 = field7;
            return this;
        }

        public Message.MessageBuilder field8(String field8) {
            this.field8 = field8;
            return this;
        }

        public Message.MessageBuilder field9(String field9) {
            this.field9 = field9;
            return this;
        }

        public Message.MessageBuilder field10(String field10) {
            this.field10 = field10;
            return this;
        }

        public Message.MessageBuilder field11(String field11) {
            this.field11 = field11;
            return this;
        }

        public Message.MessageBuilder field12(String field12) {
            this.field12 = field12;
            return this;
        }

        public Message.MessageBuilder field13(ObjectForMessage field13) {
            this.field13 = field13;
            return this;
        }

        public Message build() {
            return new Message(id, field1, field2, field3, field4, field5, field6, field7, field8, field9, field10, field11, field12, field13);
        }

        public String toString() {
            return "Message.MessageBuilder(id=" + this.id + ", field1=" + this.field1 + ", field2=" + this.field2 + ", field3=" + this.field3 + ", field4=" + this.field4 + ", field5=" + this.field5 + ", field6=" + this.field6 + ", field7=" + this.field7 + ", field8=" + this.field8 + ", field9=" + this.field9 + ", field10=" + this.field10 + ", field11=" + this.field11 + ", field12=" + this.field12 + ", field13=" + this.field13 + ")";
        }
    }
}
