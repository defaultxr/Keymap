// Keymap.sc
// class file for Emacs-style keybindings.

Keymap {
	var <bindings;
	var <boundKeys;
	var <>context;
	classvar keycodeMap, extraKeycodeMap, shiftedKeycodeMap, extraShiftedKeycodeMap, unicodeMap, shiftedUnicodeMap;
	*initClass {
		// oldKeycodeMap = [
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, "esc",
		// 	"1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
		// 	"-", "=", "backspace", "tab", "q", "w", "e", "r", "t", "y",
		// 	"u", "i", "o", "p", "[", "]", "enter", "lctrl", "a", "s",
		// 	"d", "f", "g", "h", "j", "k", "l", ";", "'", "`",
		// 	"lshift", "\\", "z", "x", "c", "v", "b", "n", "m", ",",
		// 	".", "/", "rshift", "n*", "lalt", "space", "capslock", "f1", "f2", "f3",
		// 	"f4", "f5", "f6", "f7", "f8", "f9", "f10", "numlock", nil, "n7",
		// 	"n8", "n9", "n-", "n4", "n5", "n6", "n+", "n1", "n2", "n3",
		// 	"n0", "n.", nil, nil, nil, "f11", "f12", nil, nil, nil,
		// 	nil, nil, nil, nil, "nenter", "rctrl", "n/", nil, "ralt", nil,
		// 	"home", "up", "pageup", "left", "right", "end", "down", "pagedown", nil, "delete",
		// 	nil, nil, nil, nil, nil, nil, nil, "pause", nil, nil,
		// 	nil, nil, nil, "win", nil, "menu", nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, "(", ")", nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil
		// ];
		// oldShiftedKeycodeMap = [
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	"!", "@", "#", $$.asString, "%", "^", "&", "*", "(", ")",
		// 	"_", "+", nil, nil, "Q", "W", "E", "R", "T", "Y",
		// 	"U", "I", "O", "P", "{", "}", nil, nil, "A", "S",
		// 	"D", "F", "G", "H", "J", "K", "L", ":", "\"", "~",
		// 	nil, "|", "Z", "X", "C", "V", "B", "N", "M", "<",
		// 	">", "?", nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
		// 	nil, nil, nil, nil, nil
		// ];
		keycodeMap = [
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil, // 0
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, "space", "!", "\"", "#", nil, "%", "&", "'",
			"(", ")", "*", "+", ",", "-", ".", "/", "0", "1",
			"2", "3", "4", "5", "6", "7", "8", "9", ":", ";", // 5
			"<", "=", ">", "?", "@", "A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
			"Z", "[", "\\", "]", "^", "_", nil, "a", "b", "c",
			"d", "e", "f", "g", "h", "i", "j", "k", "l", "m", // 10
			"n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
			"x", "y", "z", "{", "|", "}", nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil, // 15
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil, // 20
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil // 25
		];
		extraKeycodeMap = (
			65288:"backspace",
			65289:"tab",
			65509:"capslock",
			65505:"lshift",
			65506:"rshift",
			65507:"lctrl",
			65508:"rctrl",
			65513:"lalt",
			65514:"ralt",
			65293:"enter",
			65361:"left",
			65362:"up",
			65363:"right",
			65364:"down",
			65383:"menu",
			65307:"esc",
			65535:"delete",
		);
		shiftedKeycodeMap = [
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil, // 0
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, "!", "\"", "#", nil, "%", "&", nil,
			"(", ")", "*", "+", nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, ":", nil, // 5
			"<", nil, ">", "?", nil, "A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
			"Z", nil, nil, nil, nil, "_", nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil, // 10
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, "{", "|", "}", nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil, // 15
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil, // 20
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil // 25
		];
		extraShiftedKeycodeMap = (
			65505:"lshift",
			65506:"rshift",
			65056:"S-tab",
		);
		unicodeMap = [
			nil, "a", "b", "c", "d", "e", "f", "g", "h", "i", // ctrl+
			"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", // ctrl+
			"t", "u", "v", "w", "x", "y", "z", nil, nil, nil, // ctrl+
			nil, nil, "space", nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, "0", "1",
			"2", "3", "4", "5", "6", "7", "8", "9", nil, nil,
			nil, nil, nil, nil, nil, "A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
			"Z", nil, nil, nil, nil, nil, nil, "a", "b", "c",
			"d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
			"n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
			"x", "y", "z", nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil
		];
		shiftedUnicodeMap = [
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, "A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
			"Z", nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil, nil, nil, nil, nil, nil,
			nil, nil, nil, nil, nil
		];

	}
	*new {
		| binds=nil |
		^super.new.init(binds);
	}
	*keycode {
		| keycode |
		^if(keycode < 1000, {
			keycodeMap[keycode];
		}, {
			extraKeycodeMap[keycode];
		});
	}
	*unicode {
		| unicode |
		^unicodeMap[unicode];
	}
	*shiftedKeycode {
		| keycode |
		^if(keycode < 1000, {
			shiftedKeycodeMap[keycode];
		}, {
			extraShiftedKeycodeMap[keycode];
		});
	}
	*shiftedUnicode {
		| unicode |
		^shiftedUnicodeMap[unicode];
	}
	*stringifyKey {
		| modifiers keycode unicode |
		var output, shifted;
		if(unicode.isNil and: { keycode.isNil }, {
			output = "";
		}, {
			if(unicode.notNil, {
				shifted = Keymap.shiftedUnicode(unicode);
			}, {
				shifted = Keymap.shiftedKeycode(keycode);
			});
			output = if(modifiers.isShift, {
				if(shifted.notNil, {
					shifted;
				}, {
					"S-"++if(unicode.notNil, {
						Keymap.unicode(unicode);
					}, {
						Keymap.keycode(keycode);
					});
				});
			}, {
				if(unicode.notNil, {
					Keymap.unicode(unicode);
				}, {
					Keymap.keycode(keycode);
				});
			});
		});
		if(output.isNil, {
			^false; // unknown key...
		});
		if(["rctrl", "lctrl", "lalt", "ralt", "lshift", "rshift"].select(_==output).size > 0, {
			^nil;
		}, {
			output = if(modifiers.isAlt, "M-", "") ++ output;
			output = if(modifiers.isCtrl, "C-", "") ++ output;
			^output;
		});
	}
	init {
		| binds |
		bindings = (keymap: true);
		boundKeys = [];
		context = "";
		if(binds.notNil, {
			this.binds(binds);
		});
	}
	bind {
		| key value |
		boundKeys = this.boundKeys ++ [key];
		if(key.isKindOf(Array), {
			key.do({
				| k |
				this.bind(k, value);
			});
		}, {
			var insplit = key.asString.split($ );
			var current = value;
			insplit.reverse.do {
				| ckey |
				current = Event[ckey.asSymbol -> current, \keymap -> true];
			};
			bindings = bindings.recursivelyMerge(current);
		});
	}
	binds {
		| event |
		if(event.isKindOf(Array), {
			event.pairsDo({
				| key val |
				this.bind(key, val);
			});
		}, {
			event.keys.do({
				| key |
				this.bind(key, event[key]);
			});
		});
	}
	merge {
		| keymap |
		bindings = bindings.recursivelyMerge(keymap.bindings);
	}
	returnMerge { // like merge, but don't change the keymap's bindings, just return the merged map.
		| keymap |
		^bindings.recursivelyMerge(keymap.bindings);
	}
	at {
		| index |
		if(index.asString.includes($ ), {
			var split = index.asString.split($ );
			var current = bindings;
			split.do {
				| ckey |
				current = current[ckey.asSymbol];
			};
			^current;
		}, {
			^bindings[index.asSymbol];
		});
	}
	keyDown {
		| key | // should be a stringified Key via Keymap.stringifyKey
		var result = this[context ++ key.asString];
		if(result.isKeymap, {
			context = context ++ key.asString ++ " ";
		}, {
			context = "";
		});
		^result;
	}
	keyDownOld {
		| modifiers keycode |
		var keystring = Keymap.stringifyKey(modifiers, keycode);
		if(keystring.notNil, {
			var result = this[context ++ keystring];
			if(result.isKeymap, {
				context = context ++ keystring ++ " ";
			}, {
				context = "";
			});
			^result;
		});
	}
	helpInfo {
		var arys = this.boundKeys.reject(_.isKindOf(Array).not).flat;
		var nodupes = this.boundKeys.reject({
			| key |
			key.isKindOf(Array).not and: { arys.includes(key) };
		});
		^nodupes.collect({
			| key |
			var keystring = key.asString;
			var keylookup = if(key.isKindOf(Array), {key[0]}, {key});
			var res = this.at(keylookup);
			var resstring = case(
				{res.isKindOf(Symbol)}, {
					res.asString;
				},
				{res.isKindOf(Message)}, {
					res.cs;
				},
				{res.isKindOf(Function)}, {
					res.cs;
				},
			);
			[keystring, resstring];
		});
	}
}