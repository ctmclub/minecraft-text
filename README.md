# minecraft-text

This aims to be a simple and easy to use Java library for generating and interacting with [Minecraft JSON chat](https://wiki.vg/Chat).

The implementation is standalone of any server implementation. Basically, it can be used anywhere.

# Examples


### Hello World.

```java
TextObject text = TextBuilder.of("Hello, World.")
        .color(ChatColor.GOLD)
        .underline(true)
        .italicize(true)
        .bold(true).build();
```

This will produce a Minecraft JSON chat message that is colored gold, is underlined, is bolded, and italicized.

We can verify this by outputting the JSON:

```java
System.out.println(text.toJson().toString());
```

#### Output

```json
{
   "text":"Hello, World.",
   "color":"gold",
   "italic":true,
   "bold":true,
   "underlined":true
}
```

### Hello World (slightly more "advanced")
You can chain together multiple text objects to produce more advanced and better looking strings. Of course, you can also use
events.

```java
TextObject text = TextBuilder.of("Hello, ")
        .color(ChatColor.GREEN)
        .italicize(true)
        .append("World")
        .color(ChatColor.GOLD)
        .underline(true)
        .bold(true)
        .hoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ChatColor.AQUA + "What is this world?"))
        .clickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://en.wikipedia.org/wiki/World"))
        .append("!").italicize(true).color(ChatColor.GREEN).build();
```

And of course, this will generate a slightly larger JSON object, but it will still be valid:

```json
{
   "text":"Hello, ",
   "color":"green",
   "italic":true,
   "extra":[
      {
         "text":"World",
         "color":"gold",
         "clickEvent":{
            "action":"open_url",
            "value":"https://en.wikipedia.org/wiki/World"
         },
         "hoverEvent":{
            "action":"show_text",
            "value":"§bWhat is this world?"
         },
         "bold":true,
         "underlined":true
      },
      {
         "text":"!",
         "color":"green",
         "italic":true
      }
   ]
}
```

Throwing this text into [JSON Chat Preview](https://json.rcw.io/) (Shameless self plug) results in what we expect:

![json chat preview](https://i.imgur.com/5sQOAsD.png)

### Legacy Chat

This library supports conversion to and from [Minecraft's legacy chat](https://wiki.vg/Chat#Old_system).

We can take our text object we created and convert it to a legacy string with one simple method in the `LegacyText` class.

```java
String legacyText = LegacyText.toLegacy(text);
System.out.println(legacyText);
```

Output:

```
§a§oHello, §6§l§nWorld§a§o!
```

Exactly what we would expect!

We can even create objects from legacy strings:

```java
TextObject fromLegacy = LegacyText.fromLegacy("§a§oHello, §6§l§nWorld§a§o!");
System.out.println(fromLegacy.toJson().toString());
```

Output:
```json
{
   "text":"",
   "extra":[
      {
         "text":"Hello, ",
         "color":"green",
         "italic":true
      },
      {
         "text":"World",
         "color":"gold",
         "bold":true,
         "underlined":true
      },
      {
         "text":"!",
         "color":"green",
         "italic":true
      }
   ]
}
```

# Building

This project uses [Gradle](https://gradle.org/) for building. Simply run

```
gradlew build
```

# Dependency

Currently I would suggest just shading it into your project. I will get around to uploading it to a Maven server soon.
