package moe.rafal.configs.serdes;

import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.serdes.SerdesRegistry;
import moe.rafal.configs.serdes.component.converter.ComponentConverter;
import moe.rafal.configs.serdes.component.converter.ComponentConverterLegacy;
import moe.rafal.configs.serdes.component.converter.minimessage.ComponentConverterMiniMessage;
import moe.rafal.configs.serdes.serializer.TitleSerializer;
import moe.rafal.configs.serdes.transformer.StringToComponentTransformer;
import org.jetbrains.annotations.NotNull;

public class SerdesKyori implements OkaeriSerdesPack {

    private final ComponentConverter componentConverter;

    public SerdesKyori(ComponentConverter componentConverter) {
        this.componentConverter = componentConverter;
    }

    public SerdesKyori(boolean supportsMiniMessage) {
        this(supportsMiniMessage
            ? new ComponentConverterMiniMessage()
            : new ComponentConverterLegacy());
    }

    @Override
    public void register(@NotNull SerdesRegistry registry) {
        registry.register(new StringToComponentTransformer(componentConverter));
        registry.register(new TitleSerializer());
        registry.register(new TitleSerializer.TimesSerializer());
    }
}
