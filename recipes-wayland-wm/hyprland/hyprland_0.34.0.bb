SUMMARY = "A Wayland WM"
HOMEPAGE = "https://hyprland.org/"
SECTION = "graphics"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENSE;md5=a08367f3a4cd5301ba8b88eded36b8fd"

REQUIRED_DISTRO_FEATURES = "wayland opengl"
CONFLICT_DISTRO_FEATURES = "x11"

REQUIRED_VERSION_libdrm = "2.4.118"

DEPENDS = " \
	cairo \
	jq-native \
	libdrm \
	libinput \
	udis86 \
	virtual/egl \
	virtual/libgl \
	libxkbcommon \
	wayland \
	wayland-native \
"

SRC_URI = " \
	gitsm://github.com/hyprwm/Hyprland.git;protocol=https;branch=main \
	file://meson-build.patch \
"
SRC_URI:append:hyprland-debug = "file://enable_stdout.patch "

SRCREV = "03ebbe18ed8517ee22591eac82cd54322f42cb7d"
PV = "0.34.0"
S = "${WORKDIR}/git"

MESON_BUILDTYPE:hyprland-debug = "debug" 
EXTRA_OEMESON = "-Dxwayland=disabled -Dlegacy_renderer=disabled"

inherit meson pkgconfig features_check

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[systemd] = "-Dsystemd=enabled,-Dsystemd=disabled"

FILES:${PN} += "${datadir}"