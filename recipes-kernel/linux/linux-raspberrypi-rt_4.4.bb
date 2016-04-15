FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.6"

SRCREV = "8eacc83e68c1f2b78b38d62fe5956a6170dd0011"
SRC_URI = "git://github.com/giraldeau/linux.git;protocol=git;branch=linux-4.4.y-rt-rebase-rpi \
           file://0001-dts-add-overlay-for-pitft22.patch \
           file://rt.cfg \
"

require linux-raspberrypi.inc
