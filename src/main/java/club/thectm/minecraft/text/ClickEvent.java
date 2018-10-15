package club.thectm.minecraft.text;

import com.google.gson.JsonObject;

public final class ClickEvent {

    public enum Action {
        OPEN_URL,
        RUN_COMMAND,
        SUGGEST_COMMAND,

        // For Books
        CHANGE_PAGE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private Action action;
    private String value;

    public ClickEvent(Action action, String value) {
        this.action = action;
        this.value = value;
    }


    public JsonObject toJson() {
        JsonObject object = new JsonObject();

        object.addProperty("action", action.toString());

        // CHANGE_PAGE is an integer, the rest are Strings.
        if (this.action == Action.CHANGE_PAGE) {
            object.addProperty("value", Integer.valueOf(value));
        } else {
            object.addProperty("value", value);
        }

        return object;
    }


    public static ClickEvent fromJson(JsonObject object) {
        String action = object.getAsJsonPrimitive("action").getAsString();
        String value = object.getAsJsonPrimitive("value").getAsString();

        return new ClickEvent(Action.valueOf(action), value);
    }
}
