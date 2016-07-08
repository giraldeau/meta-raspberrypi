FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.6"

SRCREV = "8eacc83e68c1f2b78b38d62fe5956a6170dd0011"
SRC_URI = "git://github.com/giraldeau/linux.git;protocol=git;branch=linux-4.4.y-rt-rebase-rpi \
           file://0001-dts-add-overlay-for-pitft22.patch \
"

KERNEL_DEFCONFIG_raspberrypi2 ?= "bcm2835_defconfig"

require linux-raspberrypi.inc

do_config_rt() {
    # Required configuration for using kexec-tools
    kernel_configure_variable KEXEC y
    kernel_configure_variable CRASH_DUMP y
    kernel_configure_variable AUTO_ZRELADDR y

    # preempt-rt configuration
    kernel_configure_variable PREEMPT y
    kernel_configure_variable PREEMPT_RT_BASE y
    kernel_configure_variable PREEMPT_LAZY y
    kernel_configure_variable PREEMPT_RT_FULL y
    kernel_configure_variable PREEMPT_COUNT y
    kernel_configure_variable DEBUG_PREEMPT n
    yes '' | oe_runmake oldconfig
}

addtask do_config_rt before do_kernel_configcheck after do_configure
