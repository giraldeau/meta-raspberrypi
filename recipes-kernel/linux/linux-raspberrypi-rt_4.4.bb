FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.6"

SRCREV = "8eacc83e68c1f2b78b38d62fe5956a6170dd0011"
SRC_URI = "git://github.com/giraldeau/linux.git;protocol=git;branch=linux-4.4.y-rt-rebase-rpi \
           file://0001-dts-add-overlay-for-pitft22.patch \
           file://rt.cfg \
"

require linux-raspberrypi.inc

#CONFIG_PREEMPT=y
#CONFIG_PREEMPT_RT_BASE=y
#CONFIG_HAVE_PREEMPT_LAZY=y
#CONFIG_PREEMPT_LAZY=y
#CONFIG_PREEMPT_RT_FULL=y
#CONFIG_PREEMPT_COUNT=y
# CONFIG_DEBUG_PREEMPT is not set


do_config_rt() {
    kernel_configure_variable PREEMPT y
    kernel_configure_variable PREEMPT_RT_BASE y
    kernel_configure_variable PREEMPT_LAZY y
    kernel_configure_variable PREEMPT_RT_FULL y
    kernel_configure_variable PREEMPT_COUNT y
    kernel_configure_variable DEBUG_PREEMPT y
    yes '' | oe_runmake oldconfig
}


addtask do_config_rt before do_build after do_configure
