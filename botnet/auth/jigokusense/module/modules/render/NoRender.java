//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\autty\Downloads\Minecraft-Deobfuscator3000-master\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraftforge.client.event.RenderBlockOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package botnet.auth.jigokusense.module.modules.render;

import botnet.auth.jigokusense.module.Module;
import botnet.auth.jigokusense.setting.settings.SettingBoolean;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoRender
extends Module {
    SettingBoolean weather = this.register("Weather", false);
    SettingBoolean viewBobbing = this.register("ViewBobbing", false);
    SettingBoolean items = this.register("Items", false);
    SettingBoolean overlay = this.register("Overlay", false);

    public NoRender() {
        super("NoRender", "No render things", 0, Module.Category.RENDER);
    }

    @Override
    public void update() {
        if (this.weather.getValue() && NoRender.mc.world.isRaining()) {
            NoRender.mc.world.setRainStrength(0.0f);
        }
        if (this.items.getValue()) {
            NoRender.mc.world.loadedEntityList.stream().filter(EntityItem.class::isInstance).map(EntityItem.class::cast).forEach(Entity::setDead);
        }
    }

    @Override
    public void onEnable() {
        if (this.viewBobbing.getValue()) {
            NoRender.mc.gameSettings.viewBobbing = false;
        }
    }

    @Override
    public void onDisable() {
        if (this.viewBobbing.getValue()) {
            NoRender.mc.gameSettings.viewBobbing = true;
        }
    }

    @SubscribeEvent
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
        if (this.overlay.getValue()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onEvent(RenderGameOverlayEvent event) {
        if (this.overlay.getValue() && event.getType().equals((Object)RenderGameOverlayEvent.ElementType.HELMET) || event.getType().equals((Object)RenderGameOverlayEvent.ElementType.PORTAL)) {
            event.setCanceled(true);
        }
    }
}

