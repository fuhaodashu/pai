(function() {
	var BJ = "getElementsByTagName", A1 = "createElement", BL = "appendChild", A2 = "insertBefore", AO = "parentNode", A8 = document, AV = window, Ag = A8.body, BC = Math, Ay = BC.PI, AU = Ay * 2, BK = BC.pow, BE = BC.min, Ap = BC.max, Ae = BC.cos, BN = BC.sin, AY = BC.sqrt, Aq = BC.random, Ad = parseInt, AJ = /webkit/i
			.test(navigator.userAgent), AG, BD = A8.all, Ai = BD
			&& AV.XMLHttpRequest && !!A8[A1]("canvas").getContext, AL = !BD
			|| Ai, Ac = A8[BJ]("head")[0], AQ = BA("m"), BF = BA("lg"), AP = BA("kw"), AX = BF[BJ]
			("img")[0], AZ, Ao = A8[BJ]("area")[0], AN, Al = BA("s_main"), BI = __ctxPath+"/images/dragon/", g = [
			[ "head2.png", 138, 138, 1, 1 ], [ "body2.png", 42, 42, 1, 12 ],
			[ "foot2.png", 130, 130, 1, 1 ], [ "tail2.png", 20, 20, 1, 6 ],
			[ "plume2.png", 86, 86, 1, 12 ] ], AB = 75, As = [ [ 0, 85 ],
			[ 189, 70 ], [ 194, 33 ], [ 264, 26 ] ], Aw = [ 10, 25, 11, 26 ], A0 = ".drnfh{filter:fliph;transform:rotateY(180deg);-moz-transform:skew(0,180deg)scale(-1,1);-webkit-transform:rotateY(180deg);-o-transform:skew(0,180deg)scale(-1,1)}.drnb{behavior:url(#default#VML);position:absolute}.drnv{behavior:url(#default#VML);position:absolute;left:0;top:0}.drnp{position:absolute;z-index:100000;left:0;top:0;}", AT, AM = [], Ak = [], AI = 1, AD = [
			[ [ 1080, 197 ], [ 832, 45 ], [ 785, 42 ], [ 759, 84 ] ],
			[ [ 759, 84 ], [ 745, 120 ], [ 718, 107 ], [ 709, 78 ] ],
			[ [ 709, 78 ], [ 695, 46 ], [ 675, 38 ], [ 649, 63 ] ],
			[ [ 649, 63 ], [ 626, 121 ], [ 588, 89 ], [ 623, 77 ] ],
			[ [ 623, 77 ], [ 653, 68 ], [ 650, 40 ], [ 621, 47 ] ] ], Am, Au, An, Af, AS, A9, BM = 15, A6 = 0, AF = [
			[ "{ws}{ne}", 5 ], [ "[0,R]{eB}{wnD}{nB}{seD}", 5 ],
			[ "{ews}", 5 ], [ "{eB}{SW}{sB}{NE}", 5 ],
			[ "{SW}{[0,0]esBT}", 3 ], [ "{SE}{[0,0]eBT}{SE}{[0,0]sBT}", 3 ],
			[ "{es}{wn}", 3 ], [ "[2,2]{es}{wn}", 5 ] ], AK, Aa;
	function AC(F, E) {
		var H = 3 * (F[1][0] - F[0][0]), G = 3 * (F[2][0] - F[1][0]) - H, B = F[3][0]
				- F[0][0] - H - G, D = 3 * (F[1][1] - F[0][1]), C = 3
				* (F[2][1] - F[1][1]) - D, A = F[3][1] - F[0][1] - D - C;
		return [ B * BK(E, 3) + G * BK(E, 2) + H * E + F[0][0],
				A * BK(E, 3) + C * BK(E, 2) + D * E + F[0][1] ]
	}
	function BA(A) {
		return A8.getElementById(A)
	}
	function AR(C, B, A) {
		if (C.addEventListener) {
			C.addEventListener(B, A, false)
		} else {
			if (C.attachEvent) {
				C.attachEvent("on" + B, A)
			}
		}
	}
	function d(A, B) {
		return AY(BK(A[0] - B[0], 2) + BK(A[1] - B[1], 2))
	}
	function BB(B, C, A) {
		if (C[0] == B[0]) {
			if (C[1] > B[1]) {
				return Ay * 0.5
			}
			return Ay * 1.5
		} else {
			if (C[1] == B[1]) {
				if (C[0] > B[0]) {
					return 0
				}
				return Ay
			}
		}
		A = Math.atan((B[1] - C[1]) / (B[0] - C[0]));
		if (C[0] > B[0] && C[1] < B[1]) {
			return A + 2 * Ay
		}
		if (C[0] > B[0] && C[1] > B[1]) {
			return A
		}
		return A + Ay
	}
	function A5(A) {
		return Ap(Ad(d(A[0], A[1]) + d(A[1], A[2]) + d(A[2], A[3])), 1)
	}
	function A7(E, B, K, D, J) {
		B = (B + E.length) % E.length;
		var A = Ap(A5(E[B]), 1), G = K * A, F = 0, C, H, I = AC(E[B], K);
		J = J ? -1 : 1;
		while (F < D) {
			G += J;
			C = 1;
			if (G < 0 || G >= A) {
				B = (B + J + E.length) % E.length;
				A = A5(E[B]);
				F++;
				C = 0;
				if (G < 0) {
					G = A
				} else {
					G = 0
				}
			}
			K = G / A;
			H = I;
			I = AC(E[B], K);
			if (C) {
				F += d(H, I)
			}
		}
		return [ I[0], I[1], B, K ]
	}
	function BH(D, A, C, B) {
		for (C = 0; C < A.length; C++) {
			B = A[C];
			D.push([ [ B[0][0], B[0][1] ], [ B[1][0], B[1][1] ],
					[ B[2][0], B[2][1] ], [ B[3][0], B[3][1] ] ])
		}
	}
	function Az(B, C, D, A) {
		for (D = 0; D < C.length; D++) {
			A = C[D];
			m(B, [ A[0], A[1] ])
		}
	}
	function AH(A, B, C, D) {
		for (C = 0; C < A.length; C++) {
			D = A[C];
			D[0] += B[0];
			D[1] += B[1]
		}
	}
	function m(E, G) {
		var F = [ G[0], G[1] ], B = E[E.length - 1], C = B ? B[3] : F, A = [
				[ C[0], C[1] ], [ C[0], C[1] ], [ F[0], F[1] ], [ F[0], F[1] ] ], D;
		E.push(A);
		if (B) {
			D = B[0], b = A[0], c = A[3], angle = (BB(b, D) + BB(b, c)) / 2
					+ 0.5 * Ay, c1 = d(D, b) * (1 / 3), c2 = d(b, c) * (1 / 3),
					d1 = [ b[0] + Ae(angle) * c1, b[1] + BN(angle) * c1 ],
					d2 = [ b[0] + Ae(angle + Ay) * c2,
							b[1] + BN(angle + Ay) * c2 ];
			if (d(D, d1) < d(D, d2)) {
				B[2] = d1;
				A[1] = d2
			} else {
				B[2] = d2;
				A[1] = d1
			}
		}
	}
	function w() {
		var A = [], B = g[2], D = AL ? "img" : "v:image", C;
		A.push("<", D, ' class=drnv id=drnfoot0 src="', BI, B[0],
				'" style="width:', B[1] * B[3], "px;height:", B[2] * B[3],
				'px;"/>\n', "<", D, ' class=drnv id=drnfoot1 src="', BI, B[0],
				'" style="width:', B[1] * B[3], "px;height:", B[2] * B[3],
				'px;"/>\n');
		for (C = AB - 1; C >= 0; C--) {
			if (!C) {
				B = g[2];
				A.push("<", D, ' class="drnv" id="drnfoot2" src="', BI, B[0],
						'" style="width:', B[1] * B[3], "px;height:", B[2]
								* B[3], 'px;"/>\n', "<", D,
						' class="drnv" id="drnfoot3" src="', BI, B[0],
						'" style="width:', B[1] * B[3], "px;height:", B[2]
								* B[3], 'px;"/>\n')
			}
			B = g[C == 0 ? 0 : (C < AB - 2 ? 1 : 5 + C - AB)];
			AM[C] = [ B, 0, 0, 0, 0, 0, 0, 0 ];
			A.push("<", D, ' class="drnv" id="drnitem', C, '" src="', BI, B[0],
					'" style="width:', B[1] * B[3], "px;height:", B[2] * B[3],
					'px;"/>\n')
		}
		AN.innerHTML = A.join("");
		for (C = 0; C < AB; C++) {
			B = AM[C];
			B[1] = BA("drnitem" + C);
			B[2] = B[1];
			B[3] = [ 0, 0, 0, 0 ];
			if (C < AB - 2) {
				D = AC(As, C / AB);
				B[4] = D[1] / As[0][1]
			} else {
				B[4] = 1
			}
		}
		for (C = 0; C < 4; C++) {
			Ak[C] = [ g[2], BA("drnfoot" + C), BA("drnfoot" + C), 0, 1, 0, 0, 0 ]
		}
		AK = AM[0]
	}
	function Ab(B, C, A) {
		if ("" + B.style[C] == "" + A) {
			return
		}
		B.style[C] = A
	}
	Ax();
	function A3(A) {
		Au = [ A.clientX, A.clientY ];
		if (!AT) {
			var B = A.target || A.srcElement;
			if (B == Ao || B == AX || B == AZ) {
				Ar()
			}
		}
		if (AT == "run") {
			A = A || event;
			An = At()
		}
	}
	function Av(A) {
		Au = [ A.clientX || A.pageX, A.clientY || A.pageY ];
		if (!AT) {
			var B = A.target || A.srcElement || {};
			if (!(/^(input|a|area)$/i.test(B.tagName))) {
				AG = 1;
				Ar()
			}
		}
		if (AT == "run") {
			A = A || event;
			setTimeout(function() {
				An = At()
			}, 500)
		}
	}
	function Ax() {
		if (!AQ.getBoundingClientRect) {
			return
		}
		if (BD) {
			A8.createStyleSheet().cssText = A0
		} else {
			var A = A8[A1]("style");
			A.setAttribute("type", "text/css");
			A[BL](A8.createTextNode(A0));
			Ac[BL](A)
		}
		Ao.coords = "0,0,270,129";
		AN = A8[A1]("div");
		AN.className = "drnp";
		Ab(AN, "display", "none");
		AQ[BL](AN);
		AZ = AX.cloneNode(false);
		Ab(AZ, "display", "none");
		AZ.src = BI + "after.gif";
		AX.parentNode[BL](AZ);
		w();
		!Al && AR(A8, "touchstart", Av);
		AR(A8, "mousemove", A3);
		function B(C) {
			if (!AG && AT == "run") {
				AA(C.keyCode)
			}
		}
		AR(A8, "mouseup", B);
		AR(AV, "resize", B);
		AR(AV, "scroll", B);
		AR(A8, "keyup", B)
	}
	function Aj() {
		var B = [], A = AQ.getBoundingClientRect();
		BH(B, AD);
		AW(B, [ A.left - 300, 17 ]);
		return B
	}
	function At() {
		return new Date
	}
	function BG() {
		return At() - Af
	}
	function A4() {
		return At() - AS
	}
	function AA(B) {
		AT = "gohome";
		var D = Aj(), F = D.length, A, E, C;
		switch (B) {
		case 90:
			C = AF[A6++ % AF.length];
			A = Ah(C[0], C[1], 160 / C[1]);
			F += A.length;
			E = D[D.length - 1][0];
			AH(A, [ E[0] - 60, E[1] - 60 ]);
			Az(Am, A);
			break
		}
		m(Am, D[0][0]);
		BH(Am, D);
		AK[5] = Am.length - F - 1;
		AK[6] = 0;
		AS = At()
	}
	function Ar() {
		if (AT) {
			return
		}
		Af = At();
		An = At();
		AS = At();
		AT = "init";
		Am = Aj();
		AK[5] = Am.length - 1;
		AK[6] = 1;
		AE();
		A9 && clearInterval(A9);
		A9 = setInterval(function() {
			if (AT == "init") {
				var D = Ad(BE(A4() / 1000 * 100, 100));
				if (D > 60) {
					if (AL) {
						Ab(AX, "opacity", (D / 100).toFixed(2));
						Ab(AZ, "opacity", (D / 100).toFixed(2))
					} else {
						AX.style.filter = "alpha(opacity=" + D + ")";
						AZ.style.filter = "alpha(opacity=" + D + ")"
					}
					Ab(AN, "display", "");
					Ab(AX, "display", "none");
					Ab(AZ, "display", "")
				} else {
					if (AL) {
						Ab(AX, "opacity", ((100 - D) / 100).toFixed(2))
					} else {
						AX.style.filter = "alpha(opacity=" + (100 - D) + ")"
					}
				}
				if (D >= 100) {
					Aa = A8.activeElement;
					An = At();
					AS = At();
					AT = "run"
				}
			} else {
				if (AT == "run") {
					if (A4() > 50 && Au) {
						m(Am, Au);
						var A = Am[Am.length - 1], E = BB(A[3], A[0])
								+ (Ay / 8) * BN(BG() / 100);
						AW(Am, [ Ae(E) * BM * AI, BN(E) * BM * AI ]);
						var C = A7(Am, Am.length - 1, 1, 64, 1);
						AK[3][0] = C[0];
						AK[3][1] = C[1];
						AK[5] = C[2];
						AK[6] = C[3];
						AE();
						AS = At();
						if (Am.length > BM * AB * AI) {
							Am.shift()
						}
					}
					if ((!AG && At() - An > 2000) || BG() > 10000
							|| Aa != A8.activeElement) {
						AA(AG || At() - An <= 2000 ? 90 : 0)
					}
				} else {
					if (AT == "gohome") {
						var B = AK[5] == Am.length - 1 ? AI * 7 : AI
								* (A4() * 2);
						if (AK[5] < Am.length - AD.length) {
							B *= 3
						}
						var C = A7(Am, AK[5], AK[6], BE(B, 50));
						if (!isNaN(C[0]) && !isNaN(C[1])) {
							AK[3][0] = C[0];
							AK[3][1] = C[1]
						}
						AK[5] = C[2];
						AK[6] = C[3];
						AE();
						if (C[2] == Am.length - 1 && C[3] > 0.9) {
							AT = "fini";
							D = 100
						}
						AS = At()
					} else {
						if (AT == "fini") {
							var D = Ap(100 - A4() / 1000 * 100, 0);
							if (D < 40) {
								if (AL) {
									Ab(AX, "opacity", ((100 - D) / 100)
											.toFixed(2))
								} else {
									AX.style.filter = "alpha(opacity="
											+ (100 - D) + ")"
								}
							} else {
								if (AL) {
									Ab(AX, "opacity", (D / 100).toFixed(2));
									Ab(AZ, "opacity", (D / 100).toFixed(2))
								} else {
									AX.style.filter = "alpha(opacity=" + D
											+ ")";
									AZ.style.filter = "alpha(opacity=" + D
											+ ")"
								}
								Ab(AN, "display", "none");
								Ab(AX, "display", "");
								Ab(AZ, "display", "none")
							}
							if (D <= 0) {
								clearInterval(A9);
								A9 = 0;
								AT = "";
								AS = At()
							}
						}
					}
				}
			}
		}, 50)
	}
	function AW(C, B, D, E, A) {
		for (D = 0; D < C.length; D++) {
			A = C[D];
			for (E = 0; E < 4; E++) {
				A[E][0] += B[0];
				A[E][1] += B[1]
			}
		}
	}
	function AE() {
		var A, B, D, C;
		for (A = 0; A < AB; A++) {
			B = AM[A], prev = AM[A - 1] || B;
			D = AI * B[0][3] * B[4];
			C = A7(Am, prev[5], prev[6], B[0][4] * D, 1);
			B[3][0] = C[0];
			B[3][1] = C[1];
			B[5] = C[2];
			B[6] = C[3];
			B[3][2] = B[0][1] * D;
			B[3][3] = B[0][2] * D
		}
		for (A = 0; A < AB; A++) {
			B = AM[A], prev = AM[A - 1], next = AM[A + 1];
			if (prev && next) {
				B[7] = BB(prev[3], next[3])
			} else {
				if (prev) {
					B[7] = BB(prev[3], B[3])
				} else {
					if (next) {
						B[7] = BB(B[3], next[3])
					}
				}
			}
		}
		function E(F, G, H, I, J) {
			Ab(F[1], "display", "");
			Ab(F[1], "width", Ad(F[3][2]) + "px");
			Ab(F[1], "height", Ad(F[3][3]) + "px");
			Ab(F[1], "left", Ad(F[3][0] - F[3][2] / 2) + "px");
			Ab(F[1], "top", Ad(F[3][1] - F[3][3] / 2) + "px");
			I = Ad(F[7] / (2 * Ay) * 360) % 360;
			J = I >= 90 && I <= 270 ? 1 : 0;
			if (G) {
				I += ((H < 2 ? 1 : -1) * 0.35 * Ay * BN(BG() / 500)) / AU * 360
			}
			I = Ad(I);
			if (AL) {
				if (J) {
					I = "rotate(" + (I + 180) % 360 + "deg) scale(-1, 1)"
				} else {
					I = "rotate(" + I + "deg)"
				}
				if (AJ) {
					Ab(F[1], "WebkitTransform", I)
				} else {
					if (Ai) {
						Ab(F[1], "msTransform", I)
					} else {
						Ab(F[1], "Transform", I);
						Ab(F[1], "MozTransform", I);
						Ab(F[1], "OTransform", I)
					}
				}
			} else {
				Ab(F[1], "rotation", I + "deg");
				if (J) {
					F[1].src = BI + F[0][0].replace(/\.(png|gif)$/, "v.$1")
				} else {
					F[1].src = BI + F[0][0]
				}
			}
		}
		for (A = 0; A < AB; A++) {
			E(AM[A])
		}
		for (A = 0; A < 4; A++) {
			B = AM[Aw[A]];
			foot = Ak[A];
			D = AI * foot[0][3] * foot[4];
			foot[3] = [ B[3][0], B[3][1] ];
			foot[5] = B[5];
			foot[6] = B[6];
			foot[7] = B[7];
			foot[3][2] = foot[0][1] * D;
			foot[3][3] = foot[0][2] * D;
			E(foot, 1)
		}
	}
	function Ah(A, D, G) {
		function F(S, U) {
			do {
				var R = 1, T, Q, V, P = S[0];
				for (T = 0; T < P.length; T++) {
					if (S[3] && T > S[5]) {
						break
					}
					Q = P[T];
					V = S[4] ? [ S[1][0], S[1][1] ] : [ U[0], U[1] ];
					V[0] += C[Q][0];
					V[1] += C[Q][1];
					if (V[0] < 0 || V[0] >= D) {
						continue
					}
					if (V[1] < 0 || V[1] >= D) {
						continue
					}
					if (!H[V]) {
						U[0] = V[0];
						U[1] = V[1];
						if (S[3]) {
							S[5] = T
						}
						return 1
					}
					if (S[4]) {
						S[1] = [ V[0], V[1] ];
						R = 0;
						break
					}
				}
			} while (!R)
		}
		function M(Q) {
			var P = [], R = [ 0, 0 ], S = 10000000;
			Q.replace(/\[(\d+),(\d+)\]/, function(U, V, T) {
				R[+V, +T];
				return U
			}).replace(/[nswe]|SW|SE|NE|NW/g, function(T) {
				P.push(I[T])
			});
			return [ P, R, /B/.test(Q), /D/.test(Q), /T/.test(Q), S ]
		}
		var K = [], M, C = [ [ 0, -1 ], [ 0, +1 ], [ -1, 0 ], [ +1, 0 ],
				[ -1, -1 ], [ +1, -1 ], [ -1, +1 ], [ +1, +1 ] ], I = {
			n : 0,
			s : 1,
			w : 2,
			e : 3,
			NW : 4,
			NE : 5,
			SW : 6,
			SE : 7
		}, L = [ 0, 0 ], J = 0, E = [], H = {}, O, B, N;
		A.replace(/R/g, D - 1).replace(/\{(.*?)\}/g, function(Q, P) {
			K.push(M(P))
		}).replace(/\[(\d+),(\d+)\]/, function(Q, R, P) {
			L = [ +R, +P ]
		});
		E.push([ L[0] * G, L[1] * G ]);
		H[L] = 1;
		for (O = 2; O <= D * D; O++) {
			N = [ L[0], L[1] ];
			for (B = 0; B < K.length; B++) {
				M = K[J];
				if (F(M, N)) {
					if (M[2]) {
						if (M[3]) {
							M[5] = 10000000
						}
						J = (J + 1) % K.length
					}
					L = [ N[0], N[1] ];
					break
				} else {
					if (M[3]) {
						M[5] = 10000000
					}
					J = (J + 1) % K.length
				}
			}
			H[L] = O;
			E.push([ L[0] * G, L[1] * G ])
		}
		return E
	}
})();