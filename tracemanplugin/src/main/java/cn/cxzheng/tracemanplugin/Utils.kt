package cn.cxzheng.tracemanplugin

import java.io.*
import java.util.ArrayList
import java.util.zip.ZipFile

/**
 * Create by cxzheng on 2019/6/4
 */
class Utils {

    companion object {

        val BUFFER_SIZE = 16384

        fun readFileAsString(filePath: String): String {
            val fileData = StringBuffer()
            var fileReader: Reader? = null
            var inputStream: InputStream? = null
            try {
                inputStream = FileInputStream(filePath)
                fileReader = InputStreamReader(inputStream, "UTF-8")
                val buf = CharArray(BUFFER_SIZE)
                var numRead = fileReader.read(buf)
                while (numRead != -1) {
                    val readData = String(buf, 0, numRead)
                    fileData.append(readData)
                    numRead = fileReader.read(buf)
                }
            } catch (e: Exception) {

            } finally {
                try {
                    closeQuietly(fileReader)
                    closeQuietly(inputStream)
                } catch (e: Exception) {
                }

            }
            return fileData.toString()
        }


        private fun closeQuietly(obj: Any?) {
            if (obj == null) {
                return
            }
            when (obj) {
                is Closeable -> try {
                    obj.close()
                } catch (ignored: Throwable) {
                    // ignore
                }
                is AutoCloseable -> try {
                    obj.close()
                } catch (ignored: Throwable) {
                    // ignore
                }
                is ZipFile -> try {
                    obj.close()
                } catch (ignored: Throwable) {
                    // ignore
                }
                else -> throw IllegalArgumentException("obj $obj is not closeable")
            }
        }

        fun listClassFiles(classFiles: ArrayList<File>, folder: File) {
            val files = folder.listFiles() ?: return
            for (file in files) {
                if (file == null) {
                    continue
                }
                if (file.isDirectory) {
                    listClassFiles(classFiles, file)
                } else {
                    if (null != file && file.isFile) {
                        classFiles.add(file)
                    }

                }
            }
        }
    }
}