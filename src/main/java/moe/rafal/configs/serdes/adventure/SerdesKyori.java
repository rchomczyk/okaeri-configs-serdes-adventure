package moe.rafal.configs.serdes.adventure;

import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.serdes.SerdesRegistry;
import moe.rafal.configs.serdes.adventure.component.converter.ComponentConverter;
import moe.rafal.configs.serdes.adventure.component.converter.impl.LegacyComponentConverter;
import moe.rafal.configs.serdes.adventure.component.converter.impl.minimessage.MiniMessageComponentConverter;
import moe.rafal.configs.serdes.adventure.serializer.BookSerializer;
import moe.rafal.configs.serdes.adventure.serializer.BossBarSerializer;
import moe.rafal.configs.serdes.adventure.serializer.SoundSerializer;
import moe.rafal.configs.serdes.adventure.serializer.TitleSerializer;
import moe.rafal.configs.serdes.adventure.transformer.StringToComponentTransformer;
import moe.rafal.configs.serdes.adventure.transformer.StringToKeyTransformer;
import moe.rafal.configs.serdes.adventure.transformer.StringToNamedColorTransformer;
import org.jetbrains.annotations.NotNull;

public class SerdesKyori implements OkaeriSerdesPack {

    final ComponentConverter componentConverter;

    public SerdesKyori(ComponentConverter componentConverter) {
        this.componentConverter = componentConverter;
    }

    public SerdesKyori(boolean supportsMiniMessage) {
        this(supportsMiniMessage
            ? new MiniMessageComponentConverter()
            : new LegacyComponentConverter());
    }

    @Override
    public void register(@NotNull SerdesRegistry registry) {
        registry.register(new StringToComponentTransformer(componentConverter));
        registry.register(new StringToKeyTransformer());
        registry.register(new StringToNamedColorTransformer());
        registry.register(new BookSerializer());
        registry.register(new SoundSerializer());
        registry.register(new TitleSerializer());
        registry.register(new TitleSerializer.TimesSerializer());
        registry.register(new BossBarSerializer());
    }
}
