/*
 * Copyright (C) 2013 Samuel Audet
 *
 * This file is part of JavaCPP.
 *
 * JavaCPP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version (subject to the "Classpath" exception
 * as provided in the LICENSE.txt file that accompanied this code).
 *
 * JavaCPP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JavaCPP.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.bytedeco.javacpp.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

/**
 *
 * @author Samuel Audet
 */
@Properties(target="org.bytedeco.javacpp.avutil", value={
    @Platform(define="__STDC_CONSTANT_MACROS", cinclude={
        "<libavutil/avutil.h>",
        "<libavutil/buffer.h>",
        "<libavutil/channel_layout.h>",
        "<libavutil/cpu.h>",
        "<libavutil/dict.h>",
        "<libavutil/downmix_info.h>",
        "<libavutil/error.h>",
        "<libavutil/frame.h>",
        "<libavutil/imgutils.h>",
        "<libavutil/log.h>",
        "<libavutil/mathematics.h>",
        "<libavutil/mem.h>",
        "<libavutil/opt.h>",
        "<libavutil/opt.h>",
        "<libavutil/pixdesc.h>",
        "<libavutil/pixfmt.h>",
        "<libavutil/rational.h>",
        "<libavutil/samplefmt.h>",
        "<libavutil/stereo3d.h>",
        "<libavutil/time.h>"
        },
        includepath={"/usr/local/include/libav/", "/opt/local/include/libav/", "/usr/include/libav/"},
        link="avutil@.55", compiler={"default", "nodeprecated"}),
    @Platform(value="android", link="avutil")
})
public class avutil implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("AV_NOPTS_VALUE").cppTypes("int64_t").translate(false))
               .put(new Info("AV_TIME_BASE_Q").cppTypes())
               .putFirst(new Info("AVPixelFormat").valueTypes("int"))
               .putFirst(new Info("AVColorRange").valueTypes("int"))
                .putFirst(new Info("AVColorPrimaries").valueTypes("int"))
                .putFirst(new Info("AVColorSpace").valueTypes("int"))
                .putFirst(new Info("AVCodecID").valueTypes("int"))
                .putFirst(new Info("AVColorTransferCharacteristic").valueTypes("int"))
                .putFirst(new Info("AVChromaLocation").valueTypes("int"))
               .put(new Info("av_const").annotations("@Const"))
               .put(new Info("av_malloc_attrib", "av_alloc_size", "av_always_inline").cppTypes().annotations())
               .put(new Info("attribute_deprecated").annotations("@Deprecated"))
               .put(new Info("AVPanScan", "AVCodecContext").cast().pointerTypes("Pointer"))
               .put(new Info("AV_CH_LAYOUT_HEXADECAGONAL").translate().cppTypes("long"))
               .put(new Info("FF_API_AVFRAME_COLORSPACE").define(false));
    }
}
