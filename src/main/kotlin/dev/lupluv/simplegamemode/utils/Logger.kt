package dev.lupluv.simplegamemode.utils

import dev.lupluv.simplegamemode.Main
import kotlin.reflect.KClass


fun getItzLogger(clazz: KClass<*>): Logger = Logger(clazz)

class Logger(private val clazz: KClass<*>) {
    fun info(info: String) {
        Main.instance.sendConsoleMessage("§a[${clazz.simpleName}] INFO: §7$info")
    }
    fun warn(warn: String) {
        Main.instance.sendConsoleMessage("§e[${clazz.simpleName}] WARN: §7$warn")
    }
    fun error(error: String) {
        Main.instance.sendConsoleMessage("§c[${clazz.simpleName}] ERROR: §7$error")
    }
}