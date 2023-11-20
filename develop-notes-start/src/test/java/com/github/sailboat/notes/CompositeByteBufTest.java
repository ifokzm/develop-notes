package com.github.sailboat.notes;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.collections.BufferUtils;

public class CompositeByteBufTest {

    public static void main(String[] args) {
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        ByteBuf header = Unpooled.buffer();
        header.writeBytes("header".getBytes());


        ByteBuf body = Unpooled.buffer();
        body.writeBytes("body".getBytes());


        CompositeByteBuf allBuf = compositeByteBuf.addComponents(true, header, body);
        byte[] b = new byte[allBuf.capacity()];
        allBuf.readBytes(b);
        System.out.println(new String(b));

        ByteBuf merge = Unpooled.wrappedBuffer(header, body);
        byte[] b1 = new byte[merge.capacity()];
        merge.readBytes(b1);
        System.out.println(new String(b1));

    }

}
