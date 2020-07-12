# snippets

GameProfileBuilder:
Skin von einem Spieler von dem Mojang sessionserver bekommen.

Benutzung:

GameProfile skingp = new GameProfileBuilder().fetch(uuid);

cp.getProfile().getProperties().removeAll("textures");
cp.getProfile().getProperties().putAll("textures", props);
