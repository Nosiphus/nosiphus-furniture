package com.nosiphus.furniture.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShowerParticle extends TextureSheetParticle {

    protected ShowerParticle(ClientLevel clientLevel, double x, double y, double z) {
        super(clientLevel, x, y, z, 0.0D, 0.0D, 0.0D);
        this.xd *= 0.30000001192092896D;
        this.yd = -0.2D;
        this.zd *= 0.30000001192092896D;
        this.setSize(0.1F, 0.1F);
        this.lifetime = (int) 15.0D;

        this.rCol = 0.0F;
        this.gCol = 0.0F;
        this.bCol = 1.0F;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        this.rCol = 0.2F;
        this.gCol = 0.3F;
        this.bCol = 1.0F;

        if (this.lifetime-- <= 0) {
            this.remove();
        } else {
            this.move(this.xd, this.yd, this.zd);
            this.yd = -0.2D;

            if (this.onGround) {
                if (Math.random() < 0.5D) {
                    this.remove();
                }

                this.xd *= 0.699999988079071D;
                this.zd *= 0.699999988079071D;
            }

            BlockPos blockpos = new BlockPos(this.x, this.y, this.z);
            double d0 = Math.max(this.level.getBlockState(blockpos).getCollisionShape(this.level, blockpos).max(Direction.Axis.Y, this.x - (double)blockpos.getX(), this.z - (double)blockpos.getZ()), (double)this.level.getFluidState(blockpos).getHeight(this.level, blockpos));
            if (d0 > 0.0D && this.y < (double)blockpos.getY() + d0) {
                this.remove();
            }

        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double x, double y, double z, double dx, double dy, double dz) {
            ShowerParticle showerParticle = new ShowerParticle(clientLevel, x, y, z);
            showerParticle.pickSprite(this.spriteSet);
            return showerParticle;
        }

    }

}
