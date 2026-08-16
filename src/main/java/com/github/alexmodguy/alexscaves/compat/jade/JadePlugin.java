package com.github.alexmodguy.alexscaves.compat.jade;

import com.github.alexmodguy.alexscaves.server.entity.living.GumWormEntity;
import com.github.alexmodguy.alexscaves.server.entity.living.GumWormSegmentEntity;
import net.minecraft.world.entity.Entity;
import snownee.jade.api.*;

@SuppressWarnings("unused")
@WailaPlugin
public class JadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        // Make jade think the gum worm segment is the gum worm head so you can see its health and info by looking at the segments
        registration.addRayTraceCallback((hitResult, accessor, originalAccessor) -> {
            if (accessor instanceof EntityAccessor entityAccessor) {
                Entity entity = entityAccessor.getEntity();
                if (entity instanceof GumWormSegmentEntity gumWormSegment) {
                    Entity headEntity = gumWormSegment.getHeadEntity();
                    if (headEntity instanceof GumWormEntity gumWorm) {
                        return registration.entityAccessor().from(entityAccessor).entity(gumWorm).build();
                    }
                }
            }
            return accessor;
        });
    }
}
