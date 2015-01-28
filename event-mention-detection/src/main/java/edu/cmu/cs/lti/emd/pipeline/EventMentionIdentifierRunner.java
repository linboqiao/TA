package edu.cmu.cs.lti.emd.pipeline;

import edu.cmu.cs.lti.emd.annotators.EventMentionCandidateIdentifier;
import edu.cmu.cs.lti.uima.io.reader.CustomCollectionReaderFactory;
import edu.cmu.cs.lti.uima.io.writer.CustomAnalysisEngineFactory;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.uimafit.factory.TypeSystemDescriptionFactory;


public class EventMentionIdentifierRunner {
    private static String className = EventMentionIdentifierRunner.class.getSimpleName();

    public static void main(String[] args) throws UIMAException {
        System.out.println(className + " started...");
        String paramInputDir =
                "event-mention-detection/data/LDC2014E121_DEFT_Event_Nugget_Evaluation_Training_Data/data/";

        // Parameters for the writer
        String paramParentOutputDir = "event-mention-detection/data/process";
        String paramBaseOutputDirName = "semafor_processed";

        String paramTypeSystemDescriptor = "TypeSystem";


        // Instantiate the analysis engine.
        TypeSystemDescription typeSystemDescription = TypeSystemDescriptionFactory
                .createTypeSystemDescription(paramTypeSystemDescriptor);


        CollectionReaderDescription reader = CustomCollectionReaderFactory.createXmiReader(paramParentOutputDir, paramBaseOutputDirName, 0, false);

        AnalysisEngineDescription ana = CustomAnalysisEngineFactory.createAnalysisEngine(
                EventMentionCandidateIdentifier.class, typeSystemDescription);


        // Run the pipeline.
        try {
            SimplePipeline.runPipeline(reader, ana);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println(className + " successfully completed.");
    }

}
