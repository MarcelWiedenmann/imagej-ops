
package experimental.compgraph.optimization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

import net.imglib2.util.Pair;
import net.imglib2.util.ValuePair;

import experimental.compgraph.CompgraphDoubleEdge;
import experimental.compgraph.CompgraphEdge;
import experimental.compgraph.CompgraphNode;
import experimental.compgraph.CompgraphNodeBody;
import experimental.compgraph.CompgraphSingleEdge;
import experimental.compgraph.body.Map;

public class CompgraphParserTestDriven {

	// TODO: we probably need some general state monitor while parsing the graph
	@SuppressWarnings("serial")
	static class CompgraphParseBag extends HashMap<String, Object> {

		// ...

		public CompgraphParseBag(final CompgraphParseRuleEngine ruleEngine) {
			// ...
		}

		public CompgraphParseRuleEngine ruleEngine() {
			// ...
		}

		public ExecutionStage currentStage() {
			// ...
		}
	}

	// TODO: maybe use some rule-engine? ...something like this:
	static class CompgraphParseRuleEngine {

		private final HashMap<Class<? extends CompgraphNodeBody<?, ?>>, ArrayList<Pair<BiPredicate<CompgraphNodeBody<?, ?>, CompgraphParseBag>, BiConsumer<CompgraphNodeBody<?, ?>, CompgraphParseBag>>>> rules;

		public CompgraphParseRuleEngine() {
			rules = new HashMap<>();
		}

		public
			ArrayList<Pair<BiPredicate<CompgraphNodeBody<?, ?>, CompgraphParseBag>, BiConsumer<CompgraphNodeBody<?, ?>, CompgraphParseBag>>>
			getRules(final Class<? extends CompgraphNodeBody<?, ?>> nodeType)
		{
			return rules.get(nodeType);
		}

		public void addRule(final Class<? extends CompgraphNodeBody<?, ?>> nodeType,
			final BiPredicate<CompgraphNodeBody<?, ?>, CompgraphParseBag> rule,
			final BiConsumer<CompgraphNodeBody<?, ?>, CompgraphParseBag> effect)
		{
			if (!rules.containsKey(nodeType)) {
				rules.put(nodeType, new ArrayList<>());
			}
			else {
				rules.get(nodeType).add(new ValuePair<>(rule, effect));
			}
		}

		public void removeRules(final Class<? extends CompgraphNodeBody<?, ?>> nodeType) {
			rules.remove(nodeType);
		}

		public void clear() {
			rules.clear();
		}
	}

	public CompgraphParserTestDriven(
		final CompgraphNode<? extends CompgraphEdge<?>, ? extends CompgraphNodeBody<?, ?>, ? extends CompgraphSingleEdge<?>> leaf)
	{
		final CompgraphParseRuleEngine ruleEngine = new CompgraphParseRuleEngine();

		ruleEngine.addRule(Map.class, (n, hints) -> !(hints.currentStage() instanceof ParallelExecutionStage), (n,
			hints) -> hints.beginNewStage(new DefaultParallelExecutionStage()));

		parse(leaf, new CompgraphParseBag(ruleEngine));
	}

	// TODO: maybe split parsing into (at least) two phases: annotate-phase to gather complete knowledge of the entire
	// graph & annotate nodes, optimize-phase based on annotations (different rule engines for both phases):
	public void /* whatever to return ...*/ parse(
		final CompgraphNode<? extends CompgraphEdge<?>, ? extends CompgraphNodeBody<?, ?>, ? extends CompgraphSingleEdge<?>> leaf,
		final CompgraphParseBag hints)
	{
		if (leaf == null) {
			return;
		}
		// Post-order DFS:
		if (leaf.in() instanceof CompgraphSingleEdge) {
			parse(((CompgraphSingleEdge<?>) leaf.in()).source(), hints);
		}
		else if (leaf.in() instanceof CompgraphDoubleEdge) {
			final CompgraphDoubleEdge<?, ?> in = (CompgraphDoubleEdge<?, ?>) leaf.in();
			parse(in.firstSource(), hints);
			parse(in.secondSource(), hints);
		}
		else {
			throw new UnsupportedOperationException("Compgraph parsing: Single or double edge expected.");
		}
		// Process current node:
		final CompgraphNodeBody<?, ?> current = leaf.body();
		// TODO: somehow like this...
		// TODO: or perform 'simple' (i.e. local) optimizations such as merging of subsequent map-phases while modeling
		// (e.g. within factory)?
		for (final Pair<BiPredicate<CompgraphNodeBody<?, ?>, CompgraphParseBag>, BiConsumer<CompgraphNodeBody<?, ?>, CompgraphParseBag>> rule : hints
			.ruleEngine().getRules(current.getClass()))
		{
			if (rule.getA().test(current, hints)) {
				rule.getB().accept(current, hints);
			}
		}
	}
}
