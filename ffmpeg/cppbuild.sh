#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" ffmpeg
    popd
    exit
fi


# minimal configuration to support MPEG-4 streams with H.264 and AAC as well as Motion JPEG
DISABLE="--disable-iconv --disable-libxcb --disable-opencl --disable-sdl2 --disable-bzlib --disable-lzma --disable-linux-perf --disable-everything"
ENABLE="--enable-shared \
    --enable-runtime-cpudetect
    --enable-parser=h264 --enable-parser=aac
    --enable-muxer=mp4
"

NASM_VERSION=2.14
ZLIB=zlib-1.2.11
LAME=lame-3.100
SPEEX=speex-1.2.0
OPUS=opus-1.3
OPENCORE_AMR=opencore-amr-0.1.5
VO_AMRWBENC=vo-amrwbenc-0.1.3
OPENSSL=openssl-1.1.1b
OPENH264_VERSION=1.8.0
X265=3.0
VPX_VERSION=1.8.0
ALSA_VERSION=1.1.8
FREETYPE_VERSION=2.10.0
MFX_VERSION=1.25
NVCODEC_VERSION=9.0.18.1
FFMPEG_VERSION=4.1.3
download https://download.videolan.org/contrib/nasm/nasm-$NASM_VERSION.tar.gz nasm-$NASM_VERSION.tar.gz
#download http://zlib.net/$ZLIB.tar.gz $ZLIB.tar.gz
#download http://downloads.sourceforge.net/project/lame/lame/3.100/$LAME.tar.gz $LAME.tar.gz
#download http://downloads.xiph.org/releases/speex/$SPEEX.tar.gz $SPEEX.tar.gz
#download https://archive.mozilla.org/pub/opus/$OPUS.tar.gz $OPUS.tar.gz
#download http://sourceforge.net/projects/opencore-amr/files/opencore-amr/$OPENCORE_AMR.tar.gz/download $OPENCORE_AMR.tar.gz
#download http://sourceforge.net/projects/opencore-amr/files/vo-amrwbenc/$VO_AMRWBENC.tar.gz/download $VO_AMRWBENC.tar.gz
#download https://www.openssl.org/source/$OPENSSL.tar.gz $OPENSSL.tar.gz
#download https://github.com/cisco/openh264/archive/v$OPENH264_VERSION.tar.gz openh264-$OPENH264_VERSION.tar.gz
#download https://download.videolan.org/x264/snapshots/last_stable_x264.tar.bz2 last_stable_x264.tar.bz2
#download https://github.com/videolan/x265/archive/$X265.tar.gz x265-$X265.tar.gz
#download https://github.com/webmproject/libvpx/archive/v$VPX_VERSION.tar.gz libvpx-$VPX_VERSION.tar.gz
#download https://ftp.osuosl.org/pub/blfs/conglomeration/alsa-lib/alsa-lib-$ALSA_VERSION.tar.bz2 alsa-lib-$ALSA_VERSION.tar.bz2
#download https://ftp.osuosl.org/pub/blfs/conglomeration/freetype/freetype-$FREETYPE_VERSION.tar.bz2 freetype-$FREETYPE_VERSION.tar.bz2
#download https://github.com/lu-zero/mfx_dispatch/archive/$MFX_VERSION.tar.gz mfx_dispatch-$MFX_VERSION.tar.gz
#download https://github.com/FFmpeg/nv-codec-headers/archive/n$NVCODEC_VERSION.tar.gz nv-codec-headers-$NVCODEC_VERSION.tar.gz
download http://ffmpeg.org/releases/ffmpeg-$FFMPEG_VERSION.tar.bz2 ffmpeg-$FFMPEG_VERSION.tar.bz2

mkdir -p $PLATFORM
cd $PLATFORM
INSTALL_PATH=`pwd`
echo "Decompressing archives..."
tar --totals -xzf ../nasm-$NASM_VERSION.tar.gz
# tar --totals -xzf ../$ZLIB.tar.gz
# tar --totals -xzf ../$LAME.tar.gz
# tar --totals -xzf ../$SPEEX.tar.gz
# tar --totals -xzf ../$OPUS.tar.gz
# tar --totals -xzf ../$OPENCORE_AMR.tar.gz
# tar --totals -xzf ../$VO_AMRWBENC.tar.gz
# tar --totals -xzf ../$OPENSSL.tar.gz
# tar --totals -xzf ../openh264-$OPENH264_VERSION.tar.gz
# tar --totals -xjf ../last_stable_x264.tar.bz2
# tar --totals -xzf ../x265-$X265.tar.gz
# tar --totals -xzf ../libvpx-$VPX_VERSION.tar.gz
# tar --totals -xjf ../freetype-$FREETYPE_VERSION.tar.bz2
# tar --totals -xzf ../mfx_dispatch-$MFX_VERSION.tar.gz
# tar --totals -xzf ../nv-codec-headers-$NVCODEC_VERSION.tar.gz
tar --totals -xjf ../ffmpeg-$FFMPEG_VERSION.tar.bz2
# X264=`echo x264-snapshot-*`

if [[ "${ACLOCAL_PATH:-}" == C:\\msys64\\* ]]; then
    export ACLOCAL_PATH=/mingw64/share/aclocal:/usr/share/aclocal
fi

cd nasm-$NASM_VERSION
# fix for build with GCC 8.x
sedinplace 's/void pure_func/void/g' include/nasmlib.h
./configure --prefix=$INSTALL_PATH
make -j $MAKEJ V=0
make install
export PATH=$INSTALL_PATH/bin:$PATH
cd ..

#patch -Np1 -d $LAME < ../../lame.patch
#patch -Np1 -d $OPENSSL < ../../openssl-android.patch
patch -Np1 -d ffmpeg-$FFMPEG_VERSION < ../../ffmpeg.patch
#sedinplace 's/bool bEnableavx512/bool bEnableavx512 = false/g' x265-*/source/common/param.h
#sedinplace 's/detect512()/false/g' x265-*/source/common/quant.cpp

cd nasm-$NASM_VERSION

case $PLATFORM in
    android-arm)
        export AR="$ANDROID_PREFIX-ar"
        export RANLIB="$ANDROID_PREFIX-ranlib"
        export CC="$ANDROID_CC $ANDROID_FLAGS"
        export CXX="$ANDROID_CC++ $ANDROID_FLAGS"
        export STRIP="$ANDROID_PREFIX-strip"

        echo compiler $CC

        cd ../ffmpeg-$FFMPEG_VERSION
        sedinplace 's/unsigned long int/unsigned int/g' libavdevice/v4l2.c
        LDEXEFLAGS='-Wl,-rpath,\$$ORIGIN/' ./configure --prefix=.. $DISABLE $ENABLE --enable-jni --enable-mediacodec --enable-pthreads --enable-cross-compile --cross-prefix="$ANDROID_PREFIX-" --ranlib="$ANDROID_PREFIX-ranlib" --sysroot="$ANDROID_ROOT" --target-os=android --arch=arm --extra-cflags="-I../include/ $ANDROID_FLAGS" --extra-ldflags="-L../lib/ $ANDROID_FLAGS" --extra-libs="$ANDROID_LIBS -lz" --disable-symver --cc="$CC"
        make -j $MAKEJ
        make install
        ;;

    android-arm64)
        export AR="$ANDROID_PREFIX-ar"
        export RANLIB="$ANDROID_PREFIX-ranlib"
        export CC="$ANDROID_CC $ANDROID_FLAGS"
        export CXX="$ANDROID_CC++ $ANDROID_FLAGS"
        export STRIP="$ANDROID_PREFIX-strip"

        cd ../ffmpeg-$FFMPEG_VERSION
        sedinplace 's/unsigned long int/unsigned int/g' libavdevice/v4l2.c
        LDEXEFLAGS='-Wl,-rpath,\$$ORIGIN/' ./configure --prefix=.. $DISABLE $ENABLE --enable-jni --enable-mediacodec --enable-pthreads --enable-cross-compile --cross-prefix="$ANDROID_PREFIX-" --ranlib="$ANDROID_PREFIX-ranlib" --sysroot="$ANDROID_ROOT" --target-os=android --arch=aarch64 --extra-cflags="-I../include/ $ANDROID_FLAGS" --extra-ldflags="-L../lib/ $ANDROID_FLAGS" --extra-libs="$ANDROID_LIBS -lz" --disable-symver --cc="$CC"
        make -j $MAKEJ
        make install
        ;;

     android-x86)
        export AR="$ANDROID_PREFIX-ar"
        export RANLIB="$ANDROID_PREFIX-ranlib"
        export CC="$ANDROID_CC $ANDROID_FLAGS"
        export CXX="$ANDROID_CC++ $ANDROID_FLAGS"
        export STRIP="$ANDROID_PREFIX-strip"

        cd ../ffmpeg-$FFMPEG_VERSION
        sedinplace 's/unsigned long int/unsigned int/g' libavdevice/v4l2.c
        LDEXEFLAGS='-Wl,-rpath,\$$ORIGIN/' ./configure --prefix=.. $DISABLE $ENABLE --enable-jni --enable-mediacodec --enable-pthreads --enable-cross-compile --cross-prefix="$ANDROID_PREFIX-" --ranlib="$ANDROID_PREFIX-ranlib" --sysroot="$ANDROID_ROOT" --target-os=android --arch=atom --extra-cflags="-I../include/ $ANDROID_FLAGS" --extra-ldflags="-L../lib/ $ANDROID_FLAGS" --extra-libs="$ANDROID_LIBS -lz" --disable-symver --cc="$CC"
        make -j $MAKEJ
        make install
        ;;

     android-x86_64)
        export AR="$ANDROID_PREFIX-ar"
        export RANLIB="$ANDROID_PREFIX-ranlib"
        export CC="$ANDROID_CC $ANDROID_FLAGS"
        export CXX="$ANDROID_CC++ $ANDROID_FLAGS"
        export STRIP="$ANDROID_PREFIX-strip"

        cd ../ffmpeg-$FFMPEG_VERSION
        sedinplace 's/unsigned long int/unsigned int/g' libavdevice/v4l2.c
        LDEXEFLAGS='-Wl,-rpath,\$$ORIGIN/' ./configure --prefix=.. $DISABLE $ENABLE --enable-jni --enable-mediacodec --enable-pthreads --enable-cross-compile --cross-prefix="$ANDROID_PREFIX-" --ranlib="$ANDROID_PREFIX-ranlib" --sysroot="$ANDROID_ROOT" --target-os=android --arch=atom --extra-cflags="-I../include/ $ANDROID_FLAGS" --extra-ldflags="-L../lib/ $ANDROID_FLAGS" --extra-libs="$ANDROID_LIBS -lz" --disable-symver --cc="$CC"
        make -j $MAKEJ
        make install
        ;;

    *)
        echo "Error: Platform \"$PLATFORM\" is not supported"
        ;;
esac

cd ../..

