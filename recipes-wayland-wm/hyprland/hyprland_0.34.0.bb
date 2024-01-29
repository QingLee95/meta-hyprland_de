SUMMARY = "A Wayland WM"
HOMEPAGE = "https://hyprland.org/"
SECTION = "graphics"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENSE;md5=a08367f3a4cd5301ba8b88eded36b8fd"

REQUIRED_DISTRO_FEATURES = "wayland opengl"

DEPENDS += " \
	cairo \
	jq-native \
	libdrm \
	libinput \
	libxkbcommon \
	pango \
	pixman \
	tomlplusplus \
	udis86 \
	virtual/egl \
	wayland \
	wayland-native \
	wlroots \
"

RRECOMMENDS:${PN} ?= " \
	foot \
	jq \
	grim \
	slurp \
	wl-clipboard \
	hyprland-contrib \
"

SRC_URI = " \
	gitsm://github.com/hyprwm/Hyprland.git;protocol=https;branch=main \
	file://meson-build.patch \
"
SRC_URI::append:hyprland-debug = "file://enable_stdout.patch "

SRCREV = "03ebbe18ed8517ee22591eac82cd54322f42cb7d"
PV = "0.34.0"
S = "${WORKDIR}/git"

EXTRA_OEMESON = "-Dxwayland=disabled -Dlegacy_renderer=disabled"
inherit meson pkgconfig features_check

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[systemd] = "-Dsystemd=enabled,-Dsystemd=disabled"

FILES:${PN} += "${datadir}"