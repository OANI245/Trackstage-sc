package mtr.data;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import org.mtr.libraries.org.msgpack.core.MessagePacker;

import java.io.IOException;

public class Rail extends SerializedDataBase<org.mtr.core.data.Rail> {
    private final org.mtr.core.data.Rail parent;

    public Rail(org.mtr.core.data.Rail parent) {
        this.parent = parent;
    }

    public Vec3 getPosition(double rawValue) {
        return getPosition(rawValue, false);
    }

    public Vec3 getPosition(double rawValue, boolean reverse) {
        var v = parent.railMath.getPosition(rawValue, reverse);
        return new Vec3(v.x, v.y, v.z);
    }

    public double getLength() {
        return parent.railMath.getLength();
    }

    public boolean isValid() {
        return parent.isValid();
    }

    @Override
    public void toMessagePack(MessagePacker messagePacker) throws IOException {
    }

    @Override
    public int messagePackLength() {
        return 0;
    }

    @Override
    public void writePacket(FriendlyByteBuf packet) {
    }

    @Override
    public org.mtr.core.data.Rail parent() {
        return parent;
    }
}
