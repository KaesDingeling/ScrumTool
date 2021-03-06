package com.fo0.vaadin.scrumtool.ui.broadcast;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

import com.fo0.vaadin.scrumtool.ui.config.Config;
import com.fo0.vaadin.scrumtool.ui.utils.StreamUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BroadcasterUtils {

	public static void runParallel(Executor executor, Map<String, List<Consumer<String>>> listeners, String id, String message) {
		StreamUtils.parallelStream(listeners.get(id)).forEach(e -> {
			if (Config.DEBUG) {
				log.info("broadcast message '{}' to id '{}'", message, id);
			}

			executor.execute(BroadcasterUtils.execute(id, message, e));
		});
	}
	
	public static Runnable execute(String id, String message, Consumer<String> e) {
		return () -> {
			try {
				e.accept(message);
			} catch (Exception e2) {
				log.error("failed to sync {} - {}", id, message);
			}
		};
	}
	
}
