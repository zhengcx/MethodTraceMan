package cn.cxzheng.tracemanplugin

import org.objectweb.asm.Opcodes

/**
 * Create by cxzheng on 2019/6/4
 */
class TraceMethod {

    private var id: Int = 0
    private var accessFlag: Int = 0
    private var className: String? = null
    private var methodName: String? = null
    private var desc: String? = null

    companion object {
        fun create(id: Int, accessFlag: Int, className: String?, methodName: String?, desc: String?): TraceMethod {
            val traceMethod = TraceMethod()
            traceMethod.id = id
            traceMethod.accessFlag = accessFlag
            traceMethod.className = className?.replace("/", ".")
            traceMethod.methodName = methodName
            traceMethod.desc = desc?.replace("/", ".")
            return traceMethod
        }
    }


    fun getMethodNameText(): String {
        return if (desc == null || isNativeMethod()) {
            this.className + "." + this.methodName
        } else {
            this.className + "." + this.methodName + "." + desc
        }
    }



    override fun toString(): String {
        return if (desc == null || isNativeMethod()) {
            "$id,$accessFlag,$className $methodName"
        } else {
            "$id,$accessFlag,$className $methodName $desc"
        }
    }


    fun isNativeMethod(): Boolean {
        return accessFlag and Opcodes.ACC_NATIVE != 0
    }

    override fun equals(obj: Any?): Boolean {
        if (obj is TraceMethod) {
            val tm = obj as TraceMethod?
            return tm!!.getMethodNameText() == getMethodNameText()
        } else {
            return false
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}