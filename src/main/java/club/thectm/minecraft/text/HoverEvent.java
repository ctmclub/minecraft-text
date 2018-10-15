package club.thectm.minecraft.text;

import com.google.gson.JsonObject;

public final class HoverEvent {
    public enum Action {
        SHOW_TEXT,
        SHOW_ITEM,
        SHOW_ENTITY;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private Action action;
    private String value;

    public HoverEvent(Action action, String value) {
        this.action = action;
        this.value = value;
    }


    public JsonObject toJson() {
        JsonObject object = new JsonObject();

        object.addProperty("action", action.toString());
        object.addProperty("value", value);

        return object;
    }


    public static HoverEvent fromJson(JsonObject object) {
        String action = object.getAsJsonPrimitive("action").getAsString();
        String value = object.getAsJsonPrimitive("value").getAsString();

        return new HoverEvent(HoverEvent.Action.valueOf(action), value);
    }
}
