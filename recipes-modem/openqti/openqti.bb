SUMMARY = "Minimal OpenSource QTI reimplementation for Qualcomm MDM9207 userspace"
LICENSE = "MIT"
MY_PN = "openqti"
RPROVIDES_${PN} = "openqti"
PR = "r7"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://openqti.c \
           file://openqti.h \
           file://alsa_audio.h \
           file://alsa_ucm.h \
           file://mdm9607_use_cases.h \
           file://alsa_pcm.c \
           file://alsa_mixer.c \
           file://alsa_ucm.c \
           file://init_openqti"

S = "${WORKDIR}"

do_compile() {
    ${CC} ${LDFLAGS} alsa_mixer.c alsa_pcm.c alsa_ucm.c openqti.c -o openqti -lpthread
}

do_install() {
    install -d ${D}${bindir}
    install -d ${D}/etc/init.d
    install -d ${D}/etc/rcS.d

    install -m 0755 ${S}/openqti ${D}${bindir}
    install -m 0755 ${S}/init_openqti ${D}/etc/init.d/
    ln -sf -r ${D}/etc/init.d/init_openqti ${D}/etc/rcS.d/S20init_openqti
}
