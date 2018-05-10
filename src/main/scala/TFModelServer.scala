import java.nio.FloatBuffer
import java.nio.file.{Files, Paths}

import org.tensorflow.{Graph, Session, Tensor}
import scala.util.Try

case class TFModel(source: String, graph: Graph)
case class TFResult(result: Float)

class TFModelServer {
  type ResultType = Array[Array[Float]]
  val INPUT_OP = "joint_input"
  val OUTPUT_OP = "output_node0"

  def loadModel(source: String): Try[TFModel] = Try {
    val graphDef = Files.readAllBytes(Paths.get(source))

    val graph = new Graph()
    graph.importGraphDef(graphDef)

    TFModel(source, graph)
  }

  def predict(input: Array[Float], model: TFModel): Try[TFResult] = Try {
    val inputBuffer = FloatBuffer.wrap(input)
    val inputDimension: Array[Long] = Array(1, input.length).map(_.toLong)

    val session = new Session(model.graph)
    val inputTensor = Tensor.create(inputDimension, inputBuffer)

    val outputTensor = session.runner().feed(INPUT_OP, inputTensor).fetch(OUTPUT_OP).run().get(0)

    getOutput(outputTensor)
  }

  def getOutput(tensor: Tensor[_]): TFResult = {
    val result = tensor.copyTo(Array.ofDim[Float](1, 1))(0)(0)
    TFResult(result)
  }
}
