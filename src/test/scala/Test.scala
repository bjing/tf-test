// import java.io.File
// import java.io.IOException
// import java.nio.file.Files
// import org.tensorflow.Graph

import org.scalatest.{FreeSpec, Matchers}

class JsonUtilsTest extends FreeSpec with Matchers {

  "example3"  - {
    "can load graph file" in {
      assert(true)
    }
  }
}
//
//class TfModelLoadTest {
//  def testImportTfModel(): Unit = {
//    val modelFile = new File("/tmp/saved_model_1/model.pb")
//    val graphDef = Files.readAllBytes(modelFile.toPath)
//    val graph = new Nothing
//    graph.importGraphDef(graphDef)
//
//  }
//}
